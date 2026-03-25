package model;

public class Coche {	
        public String matricula;
        public int velocidad;

        public Coche(String matricula, int velocidad) {
            this.matricula = matricula;
            this.velocidad = velocidad;
        }

        @Override
        public String toString() {
            return matricula + " (" + velocidad + " km/h)";
        }
}
