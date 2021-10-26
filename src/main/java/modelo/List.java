
package modelo;

public class List {
    private long dt;
    private long sunrise;
    private long sunset;
    private Temp temp;
    private FeelsLike feelsLike;
    private long pressure;
    private long humidity;
    private Weather[] weather;
    private double speed;
    private long deg;
    private double gust;
    private long clouds;
    private double pop;
    private Double rain;

    public long getDt() { return dt; }
    public void setDt(long value) { this.dt = value; }

    public long getSunrise() { return sunrise; }
    public void setSunrise(long value) { this.sunrise = value; }

    public long getSunset() { return sunset; }
    public void setSunset(long value) { this.sunset = value; }

    public Temp getTemp() { return temp; }
    public void setTemp(Temp value) { this.temp = value; }

    public FeelsLike getFeelsLike() { return feelsLike; }
    public void setFeelsLike(FeelsLike value) { this.feelsLike = value; }

    public long getPressure() { return pressure; }
    public void setPressure(long value) { this.pressure = value; }

    public long getHumidity() { return humidity; }
    public void setHumidity(long value) { this.humidity = value; }

    public Weather[] getWeather() { return weather; }
    public void setWeather(Weather[] value) { this.weather = value; }

    public double getSpeed() { return speed; }
    public void setSpeed(double value) { this.speed = value; }

    public long getDeg() { return deg; }
    public void setDeg(long value) { this.deg = value; }

    public double getGust() { return gust; }
    public void setGust(double value) { this.gust = value; }

    public long getClouds() { return clouds; }
    public void setClouds(long value) { this.clouds = value; }

    public double getPop() { return pop; }
    public void setPop(double value) { this.pop = value; }

    public Double getRain() { return rain; }
    public void setRain(Double value) { this.rain = value; }
}