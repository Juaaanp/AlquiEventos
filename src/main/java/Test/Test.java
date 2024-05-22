package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.ModuleLayer.Controller;
import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import alquieventos.Cliente;
import alquieventos.Compra;
import alquieventos.Cupon;
import alquieventos.Evento;
import alquieventos.Factura;
import alquieventos.Localidad;
import javafx.collections.ObservableList;

public class Test {
    /**
     * Instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());
    

    @Test
    public void testRealizarCompra() {
        Localidad localidad = new Localidad(5);
        Evento evento = new Evento(LocalDate.now().plusDays(10));
        Factura factura = new Factura(LocalDate.now());
        Compra compra = new Compra(localidad, evento, factura);

        Controller controller = new Controller();
        controller.realizarCompra(compra);

        assertEquals(1, controller.getCompras().size());
        assertTrue(controller.getCompras().contains(compra));
    }

    @Test
    public void testRedimirCupones() {
        Cupon cupon1 = new Cupon(0.1);  // 10% descuento
        Cupon cupon2 = new Cupon(0.2);  // 20% descuento
        Factura factura = new Factura(LocalDate.now(), 100.0);  // Subtotal: 100.0
        Compra compra = new Compra(new Localidad(5), new Evento(LocalDate.now().plusDays(10)), factura);

        compra.setCupon(cupon1);  // Asumimos que Compra tiene un setCupon

        Controller controller = new Controller();
        controller.redimirCupones(cupon2, compra);

        assertEquals(70.0, compra.getFactura().getSubTotal());  // 100 - 30% de 100 = 70
    }

    @Test
    public void testCupoComprado() {
        Cliente cliente = new Cliente();
        Factura factura = new Factura(LocalDate.now());
        Compra compra = new Compra(new Localidad(5), new Evento(LocalDate.now().plusDays(10)), factura);
        compra.setCliente(cliente);  // Asumimos que Compra tiene un setCliente

        Controller controller = new Controller();
        controller.cupoComprado(compra);

        assertEquals(1, controller.getCupos().size());  // Verificamos que se añadió un cupo
        assertEquals(compra, cliente.getCompras().get(0));  // Verificamos que el cliente registró la compra
    }

    @Test
    public void testContarCupos() {
        Controller controller = new Controller();

        // Simulamos que se han añadido cupos
        controller.getCupos().add(1);
        controller.getCupos().add(1);
        controller.getCupos().add(1);

        int cuposComprados = controller.contarCupos();

        assertEquals(3, cuposComprados);  // Verificamos que se han contado correctamente 3 cupos
    }

    @Test
    public void testFiltrarCiudad() {
        controller.filtrarCiudad("New York");
        ObservableList<Event> filteredEvents = controller.getTblEventos().getItems();
        assertEquals(2, filteredEvents.size());
        assertEquals("Concert", filteredEvents.get(0).getName());
        assertEquals("Conference", filteredEvents.get(1).getName());
    }

    @Test
    public void testFiltrarNombre() {
        controller.filtrarNombre("Concert");
        ObservableList<Event> filteredEvents = controller.getTblEventos().getItems();
        assertEquals(1, filteredEvents.size());
        assertEquals("Concert", filteredEvents.get(0).getName());
    }

    @Test
    public void testFiltrarTipo() {
        controller.filtrarTipo("Music");
        ObservableList<Event> filteredEvents = controller.getTblEventos().getItems();
        assertEquals(1, filteredEvents.size());
        assertEquals("Concert", filteredEvents.get(0).getName());
    }
}