package com.grupo2.springboot.backend.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET,
				"/apiCliente/cliente/**",
				"/apiProd/productoNombre/**",
				"/apiInventario/inventarioGeneralCompleto/positivo",
				"/apiInventario/inventarioGeneralCompleto/positvoFiltrado/**",
				"/apiInventario/inventarioGeneralCompleto/positvoFiltradoNombre/**",
				"/apiInventario/inventarioGeneralCompleto/destacado",
				"/clienteNombre/",
				"/apiInventario/inventarioGeneralCompleto/positivo",
				"/apiCliente/ayuda/**",
				"/alerta-back/**",
				"/alerta",
				"/topic/alerta",
				"/app/alerta",
				"/apiVenta/factura/**",
				"/apiAdmin/usuario/**")
					.permitAll()
					
		.antMatchers(HttpMethod.POST,
				"/apiCliente/registro",
				"/apiCliente/recuperar",
				"/apiProd/producto",
				"/apiCompra/registro")
					.permitAll()
					
		.antMatchers(HttpMethod.PUT,
				"/apiCliente/cliente/**")
					.permitAll()
					
		.antMatchers(HttpMethod.GET,
				"/apiCarrito/carrito/**",
				"/apiVenta/ventas",
				"/apiVenta/venta/**")
					.hasAnyRole("CLIENTE")
					
		.antMatchers(HttpMethod.GET,
				"/apiPedidos/pedidos",
				"/apiPedidos/pedidosPendientes",
				"/apiPedidos/pedidosProceso",
				"/apiPedidos/pedidosCompletados",
				"/apiPedidos/pedidosCancelados",
				"/apiPedidos/pedidosCliente/**")
					.hasAnyRole("CLIENTE","ADMIN")
					
		.antMatchers(HttpMethod.POST,
				"/apiVenta/venta/**",
				"/apiPedidos/pedido")
					.hasAnyRole("CLIENTE","ADMIN")
		
		.antMatchers(HttpMethod.PUT,
				"/apiCarrito/carrito/**",
				"/apiCarrito/VaciarCarrito/**")
					.hasAnyRole("CLIENTE")
					
		.antMatchers(HttpMethod.DELETE,
				"/apiCarrito/delete")
					.hasAnyRole("CLIENTE")
					
		.antMatchers(HttpMethod.GET,
				"/apiCliente/clientes",
				"/apiAdmin/admins",
				"/apiAdmin/admin/**",
				"/apiCompra/compras",
				"/apiCompra/compra/**",
				"/apiContabilidad/contabilidadesAnuales",
				"/apiContabilidad/contabilidadAnual/**",
				"/apiContabilidad/contabilidadesAnuales/page/**",
				"/apiContabilidad/contabilidadesMensuales",
				"/apiContabilidad/contabilidadMensual/**",
				"/apiContabilidad/contabilidadesMensuales/page/**",
				"/apiContabilidad/contabilidadesDiarias",
				"/apiContabilidad/contabilidadDiaria/**",
				"/apiContabilidad/contabilidadesDiarias/page/**",
				"/apiContabilidad/llenar",
				"/apiContabilidad/contabilidadesAnuales/fecha/**",
				"/apiContabilidad/contabilidadesMensuales/fecha/**",
				"/contabilidadesDiarias/fecha/**",
				"/apiInventario/inventarioGeneralCompleto",
				"/apiInventario/inventarioDetallesCompleto",
				"/apiInventario/InvenarioGeneralId/**",
				"/apiInventario/InvenarioDetallesId/**",
				"/apiInventario/InvenarioGeneralProducto/**",
				"/apiInventario/inventarioGeneralCompleto/cantidad",
				"/apiInventario/inventarioGeneralCompleto/positvoFiltradoNombre/**",
				"/inventarioGeneralCompleto/destacado",
				"/apiProd/productos",
				"/apiProd/producto/**",
				"/apiProd/productoNombre/**",
				"/apiProd/producto/estado",
				"/apiProd/producto/estadoFiltro/**",
				"/apiVenta/ventas",
				"/apiVenta/venta/**")
					.hasAnyRole("ADMIN")
					
		.antMatchers(HttpMethod.POST,
				"/apiAdmin/registro",
				"/apiCompra/registro",
				"/apiProd/producto")
					.hasAnyRole("ADMIN")
		
		.antMatchers(HttpMethod.PUT,
				"/apiAdmin/admin/**",
				"/admin/estado/**",
				"/apiProd/producto/**",
				"/apiProd/producto/estado/**",
				"/apiPedidos/update")
					.hasAnyRole("ADMIN")
		/*.antMatchers("/api/clientes/{id}").permitAll()
		.antMatchers("/api/facturas/**").permitAll()*/
		/*.antMatchers(HttpMethod.GET, "/api/clientes/{id}").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes/upload").hasAnyRole("USER","ADMIN")
		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
		.antMatchers("/api/clientes/**").hasRole("ADMIN")*/
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource(){
		CorsConfiguration config = new CorsConfiguration(); 
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200","**","http://localhost:8090","http://localhost:8089"));
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
}
