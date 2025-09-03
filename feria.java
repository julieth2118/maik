class inventor {
    private String name;
    private  int edad;
    private int nivelcreatividad;

    public inventor(String name, int edad, int nivelcreatividad) {
        this.name = name;
        this.edad = edad;
        this.nivelcreatividad = nivelcreatividad;
    }
    
    public String getName() {
        return name;

    }
    public int getEdad() {
        return edad;
    }

    public void agregarPuntoscreatividad(int puntos) {
        this.nivelcreatividad += puntos;
        System.out.println("Nivel de creatividad actualizado: " + this.nivelcreatividad);
    }

public int obtenerNivelcreatividad() {
    return nivelcreatividad;

}

public void presentarInvento(String invento) {
    System.out.println(name + " presenta el invento: " + invento);

}
 }
class inventormecanico extends inventor {
    private String especialidad;

    public inventormecanico(String name, int edad, int nivelcreatividad, String especialidad) {
        super(name, edad, nivelcreatividad);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void repararMaquina(String maquina) {
        System.out.println(getName() + " está reparando la máquina: " + maquina);
    }
    
    public void presentarInvento(String invento) {
        System.out.println(getName() + " presenta el invento mecánico: " + invento);
    }
    public void ganapuntosenlacompetencia(String puntos) {
        System.out.println(getName() + " ha ganado " + puntos + " puntos en la competencia de inventos.");
    }

}

class inventorsoftware extends inventor {
    private String lenguajeprogramacion;

    public inventorsoftware(String name, int edad, int nivelcreatividad, String lenguajeprogramacion) {
        super(name, edad, nivelcreatividad);
        this.lenguajeprogramacion = lenguajeprogramacion;
    }

    public String getLenguajeprogramacion() {
        return lenguajeprogramacion;
    }

    public void desarrollarSoftware(String proyecto) {
        System.out.println(getName() + " está desarrollando el software: " + proyecto);
    }
    
    public void presentarInvento(String invento) {
        System.out.println(getName() + " presenta el invento de software: " + invento);
    }
    public void ganapuntosenlacompetencia(String puntos) {
        System.out.println(getName() + " ha ganado " + puntos + " puntos en la competencia de inventos.");
    }


}

class inventorquimico extends inventor {
    private String areaespecializacion;


    public inventorquimico(String name, int edad, int nivelcreatividad, String areaespecializacion) {
        super(name, edad, nivelcreatividad);
        this.areaespecializacion = areaespecializacion;
    }

    public String getAreaespecializacion() {
        return areaespecializacion;
    }

    public void realizarExperimento(String experimento) {
        System.out.println(getName() + " está realizando el experimento: " + experimento);
    }
    
    public void presentarInvento(String invento) {
        System.out.println(getName() + " presenta el invento químico: " + invento);
    }
    public void ganapuntosenlacompetencia(String puntos) {
        System.out.println(getName() + " ha ganado " + puntos + " puntos en la competencia de inventos.");
    }

}
public class feria {
    public static void main(String[] args) {
        inventormecanico inventor1 = new inventormecanico("Alicia", 30, 80, "Robótica");
        inventorsoftware inventor2 = new inventorsoftware("Bobesponja", 25, 90, "Java");
        inventorquimico inventor3 = new inventorquimico("Charles", 35, 85, "Bioquímica");
    
        inventor1.presentarInvento("Robot asistente");
        inventor1.repararMaquina("Impresora 3D");
        inventor1.ganapuntosenlacompetencia("10");
        inventor1.agregarPuntoscreatividad(10);

        inventor2.presentarInvento("Aplicación móvil");
        inventor2.desarrollarSoftware("Juego educativo");
        inventor2.ganapuntosenlacompetencia("15");
        inventor2.agregarPuntoscreatividad(15);

        inventor3.presentarInvento("Nuevo compuesto");
        inventor3.realizarExperimento("Reacción química");
        inventor3.ganapuntosenlacompetencia("Reacción química");
        inventor3.agregarPuntoscreatividad(20);
    

System.out.println("hora de presentar sus inventos al jurado");
    

for(inventor inv : new inventor[]{inventor1, inventor2, inventor3}) {
    inv.presentarInvento("Invento especial");
}
}
}











