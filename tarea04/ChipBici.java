package tarea05;


import java.time.LocalDate;
import java.time.Month;

public class ChipBici {

//Atributos
    
    public static final double MAX_DISTANCIA_TRAYECTO = 140;
    public static final int MIN_VERSION = 1;
    public static final int MAX_VERSION = 10;
    public static final int MIN_REVISION = 0;
    public static final int MAX_REVISION = 9;
    public static final int DEFAULT_VERSION = 1;
    public static final int DEFAULT_REVISION = 0;
    public static final LocalDate MIN_FECHA_ADQUISICION = LocalDate.of(2020, Month.JUNE, 15);

    public static int numBicis = 0;
    public static int numBicisAlquiladas = 0;
    public static double kmTotalesBicis = 0;
    //Estado de la bici
    private int NumSerie;
    private LocalDate fechaAdquisicion;
    private int version;
    private int revision;
    private boolean alquilada;
    private double KilometrosTotales;
    //Si esta alquilada
    private LocalDate registroInicioAlquilerActual;
    private double KilometrosRecorridosAlquilerActual;
    //Información del último alquiler
    private LocalDate registroFinUltimoAlquiler;
    private LocalDate registroInicioUltimoAlquiler;
    private double kmRecorridosAlquilerUltimo;

    //Contructor con tres parametros
    public ChipBici(LocalDate fechaAdd, int version, int revision) throws IllegalArgumentException {

        if ((fechaAdd.isBefore(MIN_FECHA_ADQUISICION) || fechaAdd.isAfter(LocalDate.now()))
                || (version < MIN_VERSION || version > MAX_VERSION)
                || (revision < MIN_REVISION || revision > MAX_REVISION)) {
            throw new IllegalArgumentException();
        }

        this.fechaAdquisicion = fechaAdd;
        this.version = version;
        this.revision = revision;
        this.NumSerie = ++numBicis; //Aumentamos el número de totalBicis en uno y y guardamos ese numero en numeroDeSerie de la nueva bici

    }

    //constructor con dos parametros
    public ChipBici(int version, int revision) {

        if ((version < MIN_VERSION || version > MAX_VERSION)
                || (revision < MIN_REVISION || revision > MAX_REVISION)) {

            throw new IllegalArgumentException();
        }

        this.version = version;
        this.revision = revision;
        NumSerie = ++numBicis;  //Primero incrementamos, despues asignamos numero de serie
//        totalBicis++;
//        this.numeroDeSerie = totalBicis;  // Es lo mismo que "this.numeroDeSerie = ++totalBicis;"
        alquilada = false;
        KilometrosTotales = 0;
        fechaAdquisicion = LocalDate.now();

    }

    //Constructor sin parametros
    public ChipBici() {
        this.NumSerie = ++numBicis;
        alquilada = false;
        KilometrosTotales = 0;
        fechaAdquisicion = LocalDate.now();
        this.version = DEFAULT_VERSION;
        this.revision = DEFAULT_REVISION;

    }

    //Método "Fábrica"
    public static ChipBici[] crearArrayChipBici(int cantidadBicis) {

        ChipBici[] arraysChips = new ChipBici[cantidadBicis];

        for (int i = 0; i < cantidadBicis; i++) {
            arraysChips[i] = new ChipBici();

        }
        return arraysChips;
    }

    // Metodo RESET
    public void reset() {

        version = MIN_VERSION;
        revision = MIN_REVISION;
        alquilada = false;
        KilometrosTotales = 0;

        registroInicioAlquilerActual = null;
        KilometrosRecorridosAlquilerActual = 0;

        registroFinUltimoAlquiler = null;
        registroInicioUltimoAlquiler = null;
        kmRecorridosAlquilerUltimo = 0;

    }

    //metodo para alquilar
    public void alquilar() {
        if (alquilada) {
            throw new IllegalStateException("Bici alquilada");
        } else {
            alquilada = true;
            registroInicioAlquilerActual = LocalDate.now();
        }

    }

    //metodo para devolver Bici
    public void devolver() {
        if (!alquilada) {
            throw new IllegalStateException("Bici no alquilada");
        } else {
            alquilada = false;
            registroFinUltimoAlquiler = LocalDate.now();
        }
    }

    //metedo de recorrer km
    public double recorrerTrayecto(double km) {
        if (!alquilada) {
            throw new IllegalStateException("Bici no alquilada");
        } else if (km < MAX_DISTANCIA_TRAYECTO) {
            throw new IllegalStateException("Distancia incorrecta: " + km);

        } else {
            KilometrosTotales += km;
            kmTotalesBicis += km;
            kmRecorridosAlquilerUltimo += km;
        }
        return kmRecorridosAlquilerUltimo;

    }

    //metodo de recorrer kmMaximos
    public double recorrerTrayecto() {
        double km = MAX_DISTANCIA_TRAYECTO;
        if (!alquilada) {
            throw new IllegalStateException("Bici no alquilada");
        } else {
            KilometrosTotales += km;
            kmTotalesBicis += km;
            kmRecorridosAlquilerUltimo += km;
        }
        return kmRecorridosAlquilerUltimo;

    }

//GETTERS
    public static double getKilometrosTodasLasBicis(){
        return kmTotalesBicis;
    }

    public static int getNumBicis() {
        return numBicis;
    }
    
    
    
    public static int getNumBicisAlquiladas() {
        return numBicisAlquiladas;
    }

    public String getVersionRevision() {
        return version + "." + revision;
    }

    public String getNumSerie() {
        return NumSerie + "";
        // return Integer.toString(numeroDeSerie);
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public int getVersion() {
        return version;
    }

    public int getRevision() {
        return revision;
    }

    public boolean isAlquilada() {
        return alquilada;
    }

    public double getKilometrosTotales() {
        return KilometrosTotales;
    }

    public LocalDate getRegistroInicioAlquilerActual() {
        return registroInicioAlquilerActual;
    }

    public double getKilometrosRecorridosAlquilerActual() {
        return KilometrosRecorridosAlquilerActual;
    }

    public LocalDate getRegistroFinUltimoAlquiler() {
        return registroFinUltimoAlquiler;
    }

    public LocalDate getRegistroInicioUltimoAlquiler() {
        return registroInicioUltimoAlquiler;
    }

    public double getKmRecorridosAlquilerUltimo() {
        return kmRecorridosAlquilerUltimo;
    }

    //Setters
    public void setNumSerie(int numeroDeSerie) {
        this.NumSerie = numeroDeSerie;
    }

    public void setFechaAdquisicion(LocalDate fechaAdd) {
        this.fechaAdquisicion = fechaAdd;
    }

    public void actualizarFirmware(int version, int revision) {
        if ((version < this.version || version > MAX_VERSION)
                || (revision < this.revision || revision > MAX_REVISION)) {

            throw new IllegalArgumentException();
        }

        this.version = version;

        this.revision = revision;
    }

    public void actualizarFirmware(int version) {
        if ((version < this.version || version > MAX_VERSION)) {

            throw new IllegalArgumentException();
        }

        this.version = version;

    }

    public void setAlquilada(boolean alquilada) {
        this.alquilada = alquilada;
    }

    public void setKmTotales(int kmTotales) {
        this.KilometrosTotales = kmTotales;
    }

    public void setRegistroInicioAlquilerActual(LocalDate fechaYHoraInicioAlquilerActual) {
        this.registroInicioAlquilerActual = fechaYHoraInicioAlquilerActual;
    }

    public void setKmRecorridosAlquilerActual(int kmRecorridosAlquilerActual) {
        this.KilometrosRecorridosAlquilerActual = kmRecorridosAlquilerActual;
    }

    public void setFechaYHoraFinUltimo(LocalDate fechaYHoraFinUltimo) {
        this.registroFinUltimoAlquiler = fechaYHoraFinUltimo;
    }

    public void setFechaYHoraInicioAlquilerUltimo(LocalDate fechaYHoraInicioAlquilerUltimo) {
        this.registroInicioUltimoAlquiler = fechaYHoraInicioAlquilerUltimo;
    }

    public void setKmRecorridosAlquilerUltimo(int kmRecorridosAlquilerUltimo) {
        this.kmRecorridosAlquilerUltimo = kmRecorridosAlquilerUltimo;
    }

}
