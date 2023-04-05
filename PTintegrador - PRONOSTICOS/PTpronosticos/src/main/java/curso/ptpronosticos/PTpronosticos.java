package curso.ptpronosticos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author yikef
 */
public class PTpronosticos {
        public static void main(String[] args) {
            
            //leer archivo Resultados y guardarlos en coleccion partidos
            Collection<Partido> partidos = new ArrayList<Partido>();
            
            //nota: archivo externo creado manualmente por fuera de netbeans
            Path pathResultados = Paths.get(args[0]);
            
            List<String> lineasResultados = null;
            
            try {
                lineasResultados = Files.readAllLines(pathResultados);
            } catch (IOException e) {
                System.out.println("NO SE PUDO LEER EL DATO DE PARTIDOS!");
                System.exit(1); //ante error sale del programa
            }
            
            // recorre archivo
            boolean primera = true;  //bandera para obviar 1era linea de titulos
            for (String lineaResultado : lineasResultados) {
                if (primera){
                    primera = false;
                }else{
                    //delimita y guarda el campo en vector campos
                    String[] campos = lineaResultado.split(","); 
                    //creamos los equipos desde el archivo externo segun su posicion
                    Equipo equipo1 = new Equipo(campos[0]);
                    Equipo equipo2 = new Equipo(campos[3]);
                    //creamos el partido
                    Partido partido = new Partido(equipo1, equipo2); 
                    //asignamos los goles al objeto creado partido, desde arch ext
                    partido.setGolesEquipo1(Integer.parseInt(campos[1]));
                    partido.setGolesEquipo2(Integer.parseInt(campos[2]));
                    //guardamos el partido en colleccion partido
                    partidos.add(partido);
             
                }
            }
            // Leer archivo pronostico, comparar con vector y sumar puntos
            //nota: archivo externo creado manualmente por fuera de netbeans 
            Path pathPronosticos = Paths.get(args[1]);
            List<String> lineasPronosticos = null;
            try {
                lineasPronosticos = Files.readAllLines(pathPronosticos);
            } catch (IOException e) {
                System.out.println("NO SE PUDO LEER EL DATO DE PRONOSTICOS!");
                System.exit(5); //ante error sale del programa 
            }
            
            // recorre archivo
            int puntos = 0;
            boolean prime = true;  //bandera para obviar 1era linea de titulos
            for (String lineaPronosticos : lineasPronosticos) {
                if (prime){
                    prime = false;
                }else{
                    //sumar los puntos
                    //delimitamos los campos y guardamos
                    String[] campos = lineaPronosticos.split(",");
                    //creamos los equipos desde el archivo externo
                    Equipo equipo1 = new Equipo(campos[0]);                                        
                    Equipo equipo2 = new Equipo(campos[4]);                                        
                    Partido partido = null;
                    
                    //busca el partido a verificar
                    for (Partido partidoCole : partidos) {
                        if(partidoCole.getEquipo1().getNombre().equals(equipo1.getNombre()) && 
                           partidoCole.getEquipo2().getNombre().equals(equipo2.getNombre())) {
                            
                                partido = partidoCole;
                        }
                    }
                    // determinamos resultado del partido
                    Equipo equipo = null;
                    EnumResultado resultado = null;
                    if ("X".equals(campos[1])){
                        equipo = equipo1;
                        resultado = EnumResultado.GANADOR;
                    }
                    if ("X".equals(campos[2])){
                        equipo = equipo1;
                        resultado = EnumResultado.EMPATE;
                    }
                    if ("X".equals(campos[3])){
                        equipo = equipo1;
                        resultado = EnumResultado.PERDEDOR;
                    }
                    // creamos el pronostico pasando los datos
                    Pronostico pronostico = new Pronostico(partido,equipo,resultado);
                    // pronostico devuelve punto 1 si acierta 0 si no acierta
                    puntos = puntos + pronostico.puntos(); // acumulamos los puntos
 
                }
            }
            // mostramos cantodad de puntos de la persona
            System.out.println("**************************************");
            System.out.println("     TOTAL DE PUNTOS ACERTADOS " + puntos);
            System.out.println("**************************************");
            
            
    }
    
}


