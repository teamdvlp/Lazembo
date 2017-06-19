package lazembo.data;

public class Giohang {
    private int id;
    private int soluong;

    public Giohang(int id,  int soluong) {
        this.id = id;
        this.soluong = soluong;
    }

    public Giohang () {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
