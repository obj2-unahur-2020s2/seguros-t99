package ar.edu.unahur.obj2.seguros

interface Seguro {
  fun costoPara(vehiculo: Vehiculo): Double
  fun puedeSerContratadoPorCamion(camion: Camion) : Boolean
  fun puedeSerContratadoPorTaxi(taxi: Taxi) : Boolean
}

class ContraTerceros : Seguro {
  override fun costoPara(vehiculo: Vehiculo): Double {
    return vehiculo.costoDeSeguroTerceros(this)
  }

  override fun puedeSerContratadoPorCamion(camion: Camion): Boolean {
    return true
  }

  override fun puedeSerContratadoPorTaxi(taxi: Taxi): Boolean {
    return true
  }
}

class RoboHurto: Seguro {
  override fun costoPara(vehiculo: Vehiculo): Double {
    return vehiculo.costoDeSeguroRoboHurto(this)
  }

  override fun puedeSerContratadoPorCamion(camion: Camion): Boolean {
    return camion.antiguedad < 10
  }

  override fun puedeSerContratadoPorTaxi(taxi: Taxi): Boolean {
    return taxi.antiguedad < 12
  }
}

class TodoRiesgo: Seguro {
  override fun costoPara(vehiculo: Vehiculo): Double {
    return vehiculo.costoDeSeguroTodoRiesgo(this)
  }

  override fun puedeSerContratadoPorCamion(camion: Camion): Boolean {
    return false
  }

  override fun puedeSerContratadoPorTaxi(taxi: Taxi): Boolean {
    return false
  }
}
