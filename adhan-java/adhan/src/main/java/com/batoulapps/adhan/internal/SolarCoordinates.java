package com.batoulapps.adhan.internal;

class SolarCoordinates {

  /**
   * The declination of the sun, the angle between
   * the rays of the Sun and the plane of the Earth's
   * equator, in degrees.
   */
  final double declination;

  /**
   *  Right ascension of the Sun, the angular distance on the
   * celestial equator from the vernal equinox to the hour circle,
   * in degrees.
   */
  final double rightAscension;

  /**
   *  Apparent sidereal time, the hour angle of the vernal
   * equinox, in degrees.
   */
  final double apparentSiderealTime;

  SolarCoordinates(double julianDay) {
    double T = CalendricalHelper.julianCentury(julianDay);
    double L0 = Astronomical.meanSolarLongitude(/* julianCentury */ T);
    double Lp = Astronomical.meanLunarLongitude(/* julianCentury */ T);
    double omega = Astronomical.ascendingLunarNodeLongitude(/* julianCentury */ T);
    double lambda = Math.toRadians(
        Astronomical.apparentSolarLongitude(/* julianCentury*/ T, /* meanLongitude */ L0));

    double theta0 = Astronomical.meanSiderealTime(/* julianCentury */ T);
    double delta_psi = Astronomical.nutationInLongitude(/* julianCentury */ T, /* solarLongitude */ L0,
        /* lunarLongitude */ Lp, /* ascendingNode */ omega);
    double delta_epsilon = Astronomical.nutationInObliquity(/* julianCentury */ T, /* solarLongitude */ L0,
        /* lunarLongitude */ Lp, /* ascendingNode */ omega);

    double _epsilon0 = Astronomical.meanObliquityOfTheEcliptic(/* julianCentury */ T);
    double _epsilonapp = Math.toRadians(Astronomical.apparentObliquityOfTheEcliptic(
        /* julianCentury */ T, /* meanObliquityOfTheEcliptic */ _epsilon0));

        /* Equation from Astronomical Algorithms page 165 */
    this.declination = Math.toDegrees(Math.asin(Math.sin(_epsilonapp) * Math.sin(lambda)));

        /* Equation from Astronomical Algorithms page 165 */
    this.rightAscension = DoubleUtil.unwindAngle(
        Math.toDegrees(Math.atan2(Math.cos(_epsilonapp) * Math.sin(lambda), Math.cos(lambda))));

        /* Equation from Astronomical Algorithms page 88 */
    this.apparentSiderealTime = theta0 + (((delta_psi * 3600) * Math.cos(Math.toRadians(_epsilon0 + delta_epsilon))) / 3600);
  }
}
