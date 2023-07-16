package org.example;

public enum Planets {
    MERCURY(0, 2439, null),
    VENUS(50, 6051, Planets.MERCURY),
    EARTH(42, 6371, Planets.VENUS),
    MARS(78, 3389, Planets.EARTH),
    JUPITER(550, 69900, Planets.MARS),
    SATURN(652, 58232, Planets.JUPITER),
    URANUS(1440, 25360, Planets.SATURN),
    NEPTUNE(1630, 24620, Planets.URANUS),
    PLUTO(1450, 1190, Planets.NEPTUNE);

    public final int distanceToPreviousPlanet;
    public final int radius;
    public final Planets previousPlanet;
    private Planets nextPlanet;
    public final int numberInTheSolarSystem;
    public final int distanceToSun;

    Planets(int distanceToPreviousPlanet, int radius, Planets previousPlanet) {
        this.distanceToPreviousPlanet = distanceToPreviousPlanet;
        this.radius = radius;
        this.numberInTheSolarSystem = this.ordinal() + 1;

        if (this.ordinal() == 0) {
            this.previousPlanet = null;
            this.distanceToSun = 58;
        } else {
            this.previousPlanet = previousPlanet;
            this.distanceToSun = distanceToPreviousPlanet + previousPlanet.distanceToSun;
            this.previousPlanet.nextPlanet = this;
        }
    }

    @Override
    public String toString() {
        String neighboursInfo;

        if (this.ordinal() == 0)
            neighboursInfo = String.format("My neighbour is %s.", this.nextPlanet.name());
        else if (this.ordinal() == Planets.values().length - 1)
            neighboursInfo = String.format("My neighbour is %s.", this.previousPlanet.name());
        else
            neighboursInfo = String.format("My neighbours are %s and %s.",
                    this.previousPlanet.name(), this.nextPlanet.name());

        return String.format("Hello! I'm %s! My Radius is %d km. I'm # %d in the Solar System. My Distance to the Sun is %d mln km. %s",
                this.name(), this.radius, this.numberInTheSolarSystem, this.distanceToSun, neighboursInfo);
    }

    public Planets getNextPlanet() {
        return this.nextPlanet;
    }
}
