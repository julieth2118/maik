import java.util.ArrayList; //no descubri por que aparece error aqui lociento jeje
// import java.util.List;


public abstract class Personaje {
    private String nombre;
    private String raza;
    private int nivelDeFuerza;
    private int nivelDeInteligencia;
    private int nivelDeteletrasportacion;
    private int nivelDehabilidadsecreta;

    public Personaje(String nombre, String raza, int nivelDeFuerza, int nivelDeInteligencia,int niveldeteletrasportacion, int nivelDehabilidadsecreta) {
        this.nombre = nombre;
        this.raza = raza;
        this.nivelDeFuerza = nivelDeFuerza;
        this.nivelDeInteligencia = nivelDeInteligencia;
        this.nivelDeInteligencia = niveldeteletrasportacion;
        this.nivelDeInteligencia = nivelDehabilidadsecreta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public int getNivelDeFuerza() {
        return nivelDeFuerza;
    }

    public int getNivelDeInteligencia() {
        return nivelDeInteligencia;
    }

public int getNiveldeteletrasportacion() {
        return nivelDeteletrasportacion;
    }

    public int getNivelDehabilidadsecreta() {
        return nivelDehabilidadsecreta;
    }

    public abstract void realizarAccion();
    
    public void describir() {
        System.out.println("Soy " + nombre + ", un " + raza + " con fuerza " + nivelDeFuerza + "  inteligencia "
         + nivelDeInteligencia + " teletraspotacion " + nivelDeteletrasportacion + "  habilidad secreta "
         + nivelDehabilidadsecreta + ".");
    }
}

class Guerrero extends Personaje {
    public Guerrero(String nombre, int nivelDeFuerza, int nivelDeInteligencia,int nivelDeteletrasportacion, int nivelDehabilidadsecreta) {
        super(nombre, "Guerrero", nivelDeFuerza, nivelDeInteligencia ,nivelDeteletrasportacion, nivelDehabilidadsecreta);
    }

    @Override
    public void realizarAccion() {
        System.out.println(getNombre() + " el poderoso gerrero ataca con una fuerza descomunal  " + getNivelDeFuerza() + "!");
    }
}

// Clase derivada Mago
class Mago extends Personaje {
    public Mago(String nombre, int nivelDeFuerza, int nivelDeInteligencia,int nivelDeteletrasportacion, int nivelDehabilidadsecreta) {
        super(nombre, "Mago", nivelDeFuerza, nivelDeInteligencia,nivelDeteletrasportacion, nivelDehabilidadsecreta);
    }

    @Override
    public void realizarAccion() {
        System.out.println(getNombre() + " le lanza un poderoso hechizó para dejarlo inconciente  " + getNivelDeInteligencia() + "!");
    }
}


class teletransportador extends Personaje {
    public teletransportador(String nombre, int nivelDeFuerza, int nivelDeInteligencia,int nivelDeteletrasportacion, int nivelDehabilidadsecreta) {
        super(nombre, "teletrasportador", nivelDeFuerza, nivelDeInteligencia ,nivelDeteletrasportacion, nivelDehabilidadsecreta);
    }

    @Override
    public void realizarAccion() {
        System.out.println(getNombre() + " se teletrasporta de un lugar a otro  " + getNivelDeFuerza() + "!");
    }
}

// Clase derivada Mago
class divergente extends Personaje {
    public divergente (String nombre, int nivelDeFuerza, int nivelDeInteligencia,int nivelDeteletrasportacion, int nivelDehabilidadsecreta) {
        super(nombre, "divergente", nivelDeFuerza, nivelDeInteligencia,nivelDeteletrasportacion, nivelDehabilidadsecreta);
    }

    @Override
    public void realizarAccion() {
        System.out.println(getNombre() + " tiene habilidades ocultas y puede tener  otros poderes " + getNivelDeInteligencia() + "!");
    }
}



// public class Reino {
    public static void main(String[] args) {
        ArrayList<Personaje> personajes = new ArrayList<>();

        
        personajes.add(new Guerrero("maik", 100, 10,0, 0)); 
        personajes.add(new Mago("alfonso", 10, 100, 0, 0));   
        personajes.add(new Guerrero("toni", 100, 10 ,0, 0));   
        personajes.add(new teletransportador("lucas", 10, 10 ,100, 0));      
        personajes.add(new divergente("meliodas", 25, 25,10, 100)); 
        personajes.add(new divergente("gojo", 25,25,10, 100));  
        personajes.add(new teletransportador("itachi", 10, 10 ,100, 0));   
        personajes.add(new Mago("sukuna", 10, 100 , 0, 0));       

       
        System.out.println("En un reino muy lejano creado por maik  donde  habitan personajes extraordinarios que guardan un gran secreto la magia y la fuerza se entrelazan, habitan valientes guerreros y sabios magos.");
        System.out.println(" quieres conocer  a algunos de estos personajes extraordinarios ?:\n");

        
        for (Personaje personaje : personajes) {
            personaje.describir();
            personaje.realizarAccion();
            System.out.println(); 
        }

        
        System.out.println("Así es como estos perosnajes  con habilidaddes super especiales y divergentes se enfrentan los desafíos de su reino. ");
    }
// }
//julieth  ochoa barrera . 