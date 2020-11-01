package ar.edu.unahur.obj2.seguros

abstract class Vehiculo(val valor: Int, val antiguedad: Int) {
  abstract fun puedeContratar(seguro: Seguro): Boolean

  private val segurosContratados = mutableListOf<Seguro>()

  fun contratar(seguro: Seguro) {
    if (this.puedeContratar(seguro)){
      segurosContratados.add(seguro)
    } else {
      error("El seguro no puede ser contratado por este vehiculo")
    }
  }

  fun costoTotalDeSegurosContratados() = segurosContratados.sumByDouble { it.costoPara(this) }

  abstract fun costoDeSeguroTerceros(contraTerceros: ContraTerceros): Double

  abstract fun costoDeSeguroRoboHurto(roboHurto: RoboHurto) : Double

  open fun costoDeSeguroTodoRiesgo(todoRiesgo: TodoRiesgo): Double {
    error("El costo de este seguro no puede calcularse para este vehiculo")
  }
}

class Auto(valor: Int, antiguedad: Int) : Vehiculo(valor, antiguedad) {

  private val modelo = 2020 - antiguedad

  override fun puedeContratar(seguro: Seguro): Boolean {
    return true
  }

  override fun costoDeSeguroTerceros(contraTerceros: ContraTerceros) = valor * 0.01

  override fun costoDeSeguroRoboHurto(roboHurto: RoboHurto): Double {
    return when {
      modelo >= 1995 -> valor * 0.03
      else           -> valor * 0.05
    }
  }

  override fun costoDeSeguroTodoRiesgo(todoRiesgo: TodoRiesgo) = valor * 0.07

}

class Camion(valor: Int, antiguedad: Int) : Vehiculo(valor, antiguedad) {
  override fun puedeContratar(seguro: Seguro): Boolean {
    return seguro.puedeSerContratadoPorCamion(this)
  }

  override fun costoDeSeguroTerceros(contraTerceros: ContraTerceros): Double {
    return when{
      antiguedad > 10 -> valor * 0.02
      else            -> valor * 0.015
    }
  }

  override fun costoDeSeguroRoboHurto(roboHurto: RoboHurto): Double {
    if (antiguedad < 10){
      return valor * 0.05
    } else {
      error("El costo de este seguro no puede calcularse para este vehiculo")
    }
  }
}

class Taxi(valor: Int, antiguedad: Int, private val tuvoInfrancciones: Boolean) : Vehiculo(valor, antiguedad) {

  private val recargoInfracciones = 1000

  override fun puedeContratar(seguro: Seguro): Boolean {
    return seguro.puedeSerContratadoPorTaxi(this)
  }

  override fun costoDeSeguroTerceros(contraTerceros: ContraTerceros): Double {
    return when {
      tuvoInfrancciones -> (valor * 0.02) + recargoInfracciones
      else              -> valor * 0.02
    }
  }

  override fun costoDeSeguroRoboHurto(roboHurto: RoboHurto): Double {
    if (antiguedad < 12){
      return valor * 0.05
    } else {
      error("El costo de este seguro no puede calcularse para este vehiculo")
    }
  }
}
