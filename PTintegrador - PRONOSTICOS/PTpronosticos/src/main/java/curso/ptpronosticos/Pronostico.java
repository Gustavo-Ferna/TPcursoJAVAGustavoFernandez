package curso.ptpronosticos;

public class Pronostico {
    
    private final Partido partido;
    private final Equipo equipo;
    private final EnumResultado resultado;

    /**
     * @param partido
     * @param equipo
     * @param resultado
     */
    public Pronostico(Partido partido, Equipo equipo, EnumResultado resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    public Partido getPartido() {
        return partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public EnumResultado getResultado() {
        return resultado;
    }
    
    // verifica los resultados para devolver un punto
    public int puntos(){
        EnumResultado resultadoReal = this.partido.resultado(this.equipo);
        if (resultado.equals(resultadoReal)){
            return 1;
        }else{
            return 0;
        }

    }
}
