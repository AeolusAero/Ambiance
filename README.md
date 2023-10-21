# Ambiance
A Java library to calculate atmospheric properties.

## Atmospheric models
- International Standard Atmosphere 1976 (ISO 2533-1975)
> [!NOTE]
> Only this atmospheric model is available at the moment. Feel free to implemement additional models :)

## Usage

Add the library to your Maven project using

```
<dependency>
  <groupId>com.aeolus_aero</groupId>
  <artifactId>ambiance</artifactId>
  <version>1.0</version>
</dependency>
```

To get started, instantiate a new Atmospheric model

```java
import com.aeolus_aero.ambiance.atmosphere.InternationalStandardAtmosphere1975;
InternationalStandardAtmosphere1975 atmo = new InternationalStandardAtmosphere1975();
```

and calculate the property you need at a given altitude

```java
double density = atmo.density(5000); // The density at 5000m geometric altitude above sea level
```
