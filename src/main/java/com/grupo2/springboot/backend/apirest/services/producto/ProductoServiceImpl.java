package com.grupo2.springboot.backend.apirest.services.producto;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IProductoDao;
import com.grupo2.springboot.backend.apirest.entity.CompraAdminVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<ProductoVo> findAll() {
		return (List<ProductoVo>) productoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ProductoVo findByCodigo_producto(int codigoP) {
		return productoDao.findById(codigoP).orElse(null);
	}

	@Override
	@Transactional
	public ProductoVo save(ProductoVo producto) {
		return productoDao.save(producto);
	}

	@Override
	public List<ProductoVo> findByNombre(String nombre) {
		return productoDao.findByNombre(nombre);
	}
	@Override
	public List<ProductoVo> findByEstado() {
		return productoDao.findByEstado();
	}

	@Override
	public List<ProductoVo> findByEstadoFiltro(String filtro) {
		return productoDao.findByEstadoFiltro(filtro);
	}

	@Override
	public void actualizarProductos(List<CompraAdminVo> detallesCompra) {
		
		for(CompraAdminVo detalle: detallesCompra) {
			productoDao.save(detalle.getCodigoProducto());
			
		}
		
	}
}
