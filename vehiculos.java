import java.util.ArrayList;
import java.util.List;


class Vehiculo {
    private String marca;
    private String modelo;
    private int año;

    public Vehiculo(String marca, String modelo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
    }


    public void mostrarInfo() {
        System.out.println("Marca: " + marca + ", Modelo: " + modelo + ", Año: " + año);
    }

    public void acelerar() {
        System.out.println("El vehículo está acelerando.");
    }
}


class Coche extends Vehiculo {
    private int puertas;

    public Coche(String marca, String modelo, int año, int puertas) {
        super(marca, modelo, año);
        this.puertas = puertas;
    }

   
    @Override
    public void acelerar() {
        System.out.println("El coche está acelerando rápidamente.");
    }

  
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Puertas: " + puertas);
    }
}

class Moto extends Vehiculo {
    private boolean tieneSidecar;

   
    public Moto(String marca, String modelo, int año, boolean tieneSidecar) {
        super(marca, modelo, año);
        this.tieneSidecar = tieneSidecar;
    }

    
    @Override
    public void acelerar() {
        System.out.println("La moto está acelerando con agilidad.");
    }

   
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("¿Tiene sidecar? " + (tieneSidecar ? "Sí" : "No"));
    }
}


public class Main {
    public static void main(String[] args) {
        
        List<Vehiculo> vehiculos = new ArrayList<>();

      
        Vehiculo vehiculoBase = new Vehiculo("Genérico", "Modelo X", 2020);
        Coche coche = new Coche("Toyota", "Corolla", 2021, 4);
        Moto moto = new Moto("Harley-Davidson", "Sportster", 2022, false);

       
        vehiculos.add(vehiculoBase);
        vehiculos.add(coche);
        vehiculos.add(moto);

       
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.mostrarInfo();
            vehiculo.acelerar();
            System.out.println();
        }
    }
}