package com.backend.PIBack.service.impl;

import com.backend.PIBack.dto.FavoritoDto;
import com.backend.PIBack.dto.ProductoDto;
import com.backend.PIBack.dto.ProductoImagenDto;
import com.backend.PIBack.dto.UsuarioDto;
import com.backend.PIBack.entity.Favorito;
import com.backend.PIBack.entity.Imagen;
import com.backend.PIBack.entity.Producto;
import com.backend.PIBack.entity.Usuario;
import com.backend.PIBack.repository.FavoritoRepository;
import com.backend.PIBack.repository.ProductoRepository;
import com.backend.PIBack.repository.UsuarioRepository;
import com.backend.PIBack.service.IFavoritoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoService implements IFavoritoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FavoritoService.class);

    private final FavoritoRepository favoritoRepository;

    private final UsuarioRepository usuarioRepository;

    private final ProductoRepository productoRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public FavoritoService(FavoritoRepository favoritoRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository, ObjectMapper objectMapper) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public FavoritoDto registrarFavorito(Favorito favorito) {
        FavoritoDto favoritoDto;
        Usuario usuario = usuarioRepository.findByEmail(favorito.getUsuario().getEmail()).orElse(null);
        Producto producto = productoRepository.findById(favorito.getProducto().getId()).orElse(null);


        UsuarioDto usuarioDto = objectMapper.convertValue(usuario, UsuarioDto.class);
        ProductoDto productoDto = convertirAProductoDto(producto);


        if (usuario == null) {
            LOGGER.info("No existe el usuario con id: {}", favorito.getUsuario().getId());
            return null;
        }

        if (producto == null) {
            LOGGER.info("No existe el producto con id: {}", favorito.getProducto().getId());
            return null;
        }

        favorito.setUsuario(usuario);

        Favorito favoritoG = favoritoRepository.save(favorito);


        favoritoDto = new FavoritoDto(favoritoG.getId(), usuarioDto, productoDto);


        LOGGER.info("Se guardó como favorito: {}", favoritoDto);
        return favoritoDto;
    }

    @Override
    public FavoritoDto buscarFavoritoPorId(Long id) {
        Favorito favoritoBuscado = favoritoRepository.findById(id).orElse(null);
        FavoritoDto favoritoDto = null;

        Producto producto = productoRepository.findById(favoritoBuscado.getProducto().getId()).orElse(null);

        ProductoDto productoDto = convertirAProductoDto(producto);

        if (favoritoBuscado != null) {
            favoritoDto = objectMapper.convertValue(favoritoBuscado, FavoritoDto.class);
            LOGGER.info("Favorito encontrado: {}", favoritoDto);
        } else {
            LOGGER.error("El favorito buscado con id {}, no se encuentra registrado en la base de datos", id);
        }
        return favoritoDto;
    }

    @Override
    public List<FavoritoDto> obtenerProductosFavoritosDeUsuarioPorId(Long usuarioId) {
        List<Favorito> favoritos = favoritoRepository.findByUsuarioId(usuarioId);

        return favoritos.stream()
                .map(favorito -> {
                    UsuarioDto usuarioDto = objectMapper.convertValue(favorito.getUsuario(), UsuarioDto.class);
                    ProductoDto productoDto = convertirAProductoDto(favorito.getProducto());

                    return new FavoritoDto(favorito.getId(), usuarioDto, productoDto);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FavoritoDto> obtenerProductosFavoritosDeUsuarioPorEmail(String usuarioEmail) {
        List<Favorito> favoritos = favoritoRepository.findByUsuarioEmail(usuarioEmail);

        LOGGER.info(favoritoRepository.findByUsuarioEmail(usuarioEmail).toString());

        return favoritos.stream()
                .map(favorito -> {
                    UsuarioDto usuarioDto = objectMapper.convertValue(favorito.getUsuario(), UsuarioDto.class);
                    ProductoDto productoDto = convertirAProductoDto(favorito.getProducto());

                    return new FavoritoDto(favorito.getId(), usuarioDto, productoDto);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FavoritoDto> listarFavoritos() {
        List<Favorito> favoritos = favoritoRepository.findAll();

        List<FavoritoDto> favoritoDtos = favoritos.stream().map(favorito -> {

            Usuario usuario = usuarioRepository.findByEmail(favorito.getUsuario().getEmail()).orElse(null);
            Producto producto = productoRepository.findById(favorito.getProducto().getId()).orElse(null);


            UsuarioDto usuarioDto = objectMapper.convertValue(usuario, UsuarioDto.class);
            ProductoDto productoDto = convertirAProductoDto(producto);


            return new FavoritoDto(favorito.getId(), usuarioDto, productoDto);
        }).toList();

        if (favoritoDtos.size() > 0) {
            LOGGER.info("Listado de favoritos: {}", favoritoDtos);
        } else {
            LOGGER.warn("No existen favoritos registrados en la base de datos: {}", favoritoDtos);
        }

        return favoritoDtos;
    }

    @Override
    public FavoritoDto actualizarFavorito(Favorito favorito) {
        Favorito favoritoAActualizar = favoritoRepository.findById(favorito.getId()).orElse(null);
        FavoritoDto favoritoActualizadoDto = null;
        Usuario usuario;
        UsuarioDto usuarioDto = null;
        Producto producto;
        ProductoDto productoDto = null;


        if (favoritoAActualizar != null) {
            if (favorito.getUsuario() != null) {
                usuario = usuarioRepository.findByEmail(favorito.getUsuario().getEmail()).orElse(null);
                favoritoAActualizar.setUsuario(usuario);

                usuarioDto = objectMapper.convertValue(usuario, UsuarioDto.class);
            }

            if (favorito.getProducto() != null) {
                producto = productoRepository.findById(favorito.getProducto().getId()).orElse(null);
                favoritoAActualizar.setProducto(producto);

                productoDto = convertirAProductoDto(producto);
            }


            favoritoActualizadoDto = objectMapper.convertValue(favoritoRepository.save(favoritoAActualizar), FavoritoDto.class);


            favoritoActualizadoDto.setUsuario(usuarioDto);
            favoritoActualizadoDto.setProducto(productoDto);

            LOGGER.info("El favorito con ID {} ha sido actualizado: {}", favorito.getId(), favoritoActualizadoDto);
        } else {
            LOGGER.warn("No es posible actualizar el favorito porque no está registrado en la base de datos");
        }
        return favoritoActualizadoDto;
    }

    @Override
    public void eliminarFavorito(Long id) {
        if (buscarFavoritoPorId(id) != null) {
            favoritoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el favorito con ID: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el favorito con id " + id);
        }
    }

    public static List<String> obtenerUrls(List<Imagen> imagenes) {
        List<String> urls = imagenes
                .stream()
                .map(Imagen::getUrl) // Mapear cada Imagen a su URL
                .collect(Collectors.toList());

        return urls;
    }


    private ProductoDto convertirAProductoDto(Producto producto) {
        List<ProductoImagenDto> imagenesDto = producto.getImagenes()
                .stream()
                .map(imagen -> objectMapper.convertValue(imagen, ProductoImagenDto.class))
                .collect(Collectors.toList());

        return new ProductoDto(
                producto.getId(), producto.getNombre(), producto.getDescripcion(), imagenesDto, producto.getCategoria(), producto.getCaracteristicas()
        );
    }
}
