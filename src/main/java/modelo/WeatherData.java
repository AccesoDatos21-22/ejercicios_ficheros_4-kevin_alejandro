package modelo;

public class WeatherData {
    private City city;
    private String cod;
    private double message;
    private long cnt;
    private List[] list;

    public City getCity() { return city; }
    public void setCity(City value) { this.city = value; }

    public String getCod() { return cod; }
    public void setCod(String value) { this.cod = value; }

    public double getMessage() { return message; }
    public void setMessage(double value) { this.message = value; }

    public long getCnt() { return cnt; }
    public void setCnt(long value) { this.cnt = value; }

    public List[] getList() { return list; }
    public void setList(List[] value) { this.list = value; }
}