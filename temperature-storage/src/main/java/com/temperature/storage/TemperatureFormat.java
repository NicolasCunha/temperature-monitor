package com.temperature.storage;

public enum TemperatureFormat {

    CELSIUS {
        @Override
        public double toCelsius(final Double reading) {
            return reading;
        }

        @Override
        public double toFahrenheit(final Double reading) {
            return (reading * 1.8) + 32;
        }

        @Override
        public double toKelvin(final Double reading) {
            return reading + 273.15;
        }
    },
    FAHRENHEIT {
        @Override
        public double toCelsius(final Double reading) {
            return (reading - 32) / 1.8;
        }

        @Override
        public double toFahrenheit(final Double reading) {
            return reading;
        }

        @Override
        public double toKelvin(final Double reading) {
            return (reading + 459.67) / 1.8;
        }
    },
    KELVIN {
        @Override
        public double toCelsius(final Double reading) {
            return reading - 273.15;
        }

        @Override
        public double toFahrenheit(final Double reading) {
            return (reading * 1.8) - 459.67;
        }

        @Override
        public double toKelvin(final Double reading) {
            return reading;
        }
    };

    public abstract double toCelsius(final Double reading);
    public abstract double toFahrenheit(final Double reading);
    public abstract double toKelvin(final Double reading);

}
