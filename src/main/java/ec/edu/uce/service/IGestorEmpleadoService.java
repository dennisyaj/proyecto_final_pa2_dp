package ec.edu.uce.service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Vehiculo;

public interface IGestorEmpleadoService {

	void registrarCliente(Cliente cliente);

	Cliente buscarCliente(String cedula);

	void ingresarVehiculo(Vehiculo vehiculo);

	Vehiculo buscarVehiculo(String placa);

	String retirarVehiculoReservado(String numeroReserva);

	void retirarVehiculoSinReserva();

}
