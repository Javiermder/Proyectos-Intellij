public class Cuenta {
    float saldo ;

   public Cuenta(float saldo) {
       this.saldo = saldo;
   }

   public Cuenta(){
       this.saldo = 0;
   }

    synchronized public float getSaldo() {
       return saldo;
    }

    synchronized public void setSaldo(float saldo) {
       this.saldo = saldo;
    }

    synchronized public void incrementarSaldo(float saldo) {
        this.saldo += saldo;
    }

    synchronized public boolean decrementarSaldo(float saldo) {
       if(this.saldo >= saldo) {
           this.saldo -= saldo;
           return true;
       }
       return false;
       }

}
