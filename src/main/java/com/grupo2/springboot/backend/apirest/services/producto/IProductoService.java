package com.grupo2.springboot.backend.apirest.services.producto;

import java.util.List;
import java.util.Optional;

import com.grupo2.springboot.backend.apirest.entity.CompraAdminVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;

public interface IProductoService {

	public List<ProductoVo> findAll();
	
	public ProductoVo findByCodigo_producto(int codigoP);
	
	public ProductoVo save(ProductoVo producto);
	
	public List<ProductoVo> findByNombre(String nombre);
	
	
	public List<ProductoVo> findByEstado();
	
	public List<ProductoVo> findByEstadoFiltro(String filtro);
	
	public void actualizarProductos(List<CompraAdminVo> detallesCompra);
}
