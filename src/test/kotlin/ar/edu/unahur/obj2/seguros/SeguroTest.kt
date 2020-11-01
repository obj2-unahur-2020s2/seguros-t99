package ar.edu.unahur.obj2.seguros

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SeguroTest : DescribeSpec({
  describe("Seguros") {

    val seguroTerceros = ContraTerceros()
    val seguroRoboHurto = RoboHurto()
    val seguroTodoRiesgo = TodoRiesgo()

    val autoViejo = Auto(50000,30)
    val autoNuevo = Auto(100000,10)
    val camionViejo = Camion(200000,20)
    val camionNuevo = Camion(500000,9)
    val taxiViejoConInfra = Taxi(80000,15,true)
    val taxiViejoSinInfra = Taxi(90000,15,false)
    val taxiNuevoConInfra = Taxi(120000,10,true)
    val taxiNuevoSinInfra = Taxi(150000,10,false)

    describe("Requerimiento 1: Pueden ser contratados"){
      it("Contra terceros"){
        autoViejo.puedeContratar(seguroTerceros).shouldBeTrue()
        autoNuevo.puedeContratar(seguroTerceros).shouldBeTrue()
        camionViejo.puedeContratar(seguroTerceros).shouldBeTrue()
        camionNuevo.puedeContratar(seguroTerceros).shouldBeTrue()
        taxiViejoConInfra.puedeContratar(seguroTerceros).shouldBeTrue()
        taxiViejoSinInfra.puedeContratar(seguroTerceros).shouldBeTrue()
        taxiNuevoConInfra.puedeContratar(seguroTerceros).shouldBeTrue()
        taxiNuevoSinInfra.puedeContratar(seguroTerceros).shouldBeTrue()
      }
      it("Robo/Hurto"){
        autoViejo.puedeContratar(seguroRoboHurto).shouldBeTrue()
        autoNuevo.puedeContratar(seguroRoboHurto).shouldBeTrue()
        camionViejo.puedeContratar(seguroRoboHurto).shouldBeFalse()
        camionNuevo.puedeContratar(seguroRoboHurto).shouldBeTrue()
        taxiViejoConInfra.puedeContratar(seguroRoboHurto).shouldBeFalse()
        taxiViejoSinInfra.puedeContratar(seguroRoboHurto).shouldBeFalse()
        taxiNuevoConInfra.puedeContratar(seguroRoboHurto).shouldBeTrue()
        taxiNuevoSinInfra.puedeContratar(seguroRoboHurto).shouldBeTrue()
      }
      it("Todo riesgo"){
        autoViejo.puedeContratar(seguroTodoRiesgo).shouldBeTrue()
        autoNuevo.puedeContratar(seguroTodoRiesgo).shouldBeTrue()
        camionViejo.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
        camionNuevo.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
        taxiViejoConInfra.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
        taxiViejoSinInfra.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
        taxiNuevoConInfra.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
        taxiNuevoSinInfra.puedeContratar(seguroTodoRiesgo).shouldBeFalse()
      }
    }
    describe("Requerimiento 2: Costo de seguro para determinado vehiculo"){
      it("Contra terceros"){
        seguroTerceros.costoPara(autoViejo).shouldBe(500)
        seguroTerceros.costoPara(autoNuevo).shouldBe(1000)
        seguroTerceros.costoPara(camionViejo).shouldBe(4000)
        seguroTerceros.costoPara(camionNuevo).shouldBe(7500)
        seguroTerceros.costoPara(taxiViejoConInfra).shouldBe(2600)
        seguroTerceros.costoPara(taxiViejoSinInfra).shouldBe(1800)
        seguroTerceros.costoPara(taxiNuevoConInfra).shouldBe(3400)
        seguroTerceros.costoPara(taxiNuevoSinInfra).shouldBe(3000)
      }
      it("Robo/Hurto"){
        seguroRoboHurto.costoPara(autoViejo).shouldBe(2500)
        seguroRoboHurto.costoPara(autoNuevo).shouldBe(3000)
        shouldThrowAny { seguroRoboHurto.costoPara(camionViejo) }
        seguroRoboHurto.costoPara(camionNuevo).shouldBe(25000)
        shouldThrowAny { seguroRoboHurto.costoPara(taxiViejoConInfra) }
        shouldThrowAny { seguroRoboHurto.costoPara(taxiViejoSinInfra) }
        seguroRoboHurto.costoPara(taxiNuevoConInfra).shouldBe(6000)
        seguroRoboHurto.costoPara(taxiNuevoSinInfra).shouldBe(7500)
      }
      it("Todo riesgo"){
        seguroTodoRiesgo.costoPara(autoViejo).shouldBe(3500.0000000000005)
        seguroTodoRiesgo.costoPara(autoNuevo).shouldBe(7000.000000000001)
        shouldThrowAny { seguroTodoRiesgo.costoPara(camionViejo) }
        shouldThrowAny { seguroTodoRiesgo.costoPara(camionNuevo) }
        shouldThrowAny { seguroTodoRiesgo.costoPara(taxiViejoConInfra) }
        shouldThrowAny { seguroTodoRiesgo.costoPara(taxiViejoSinInfra) }
        shouldThrowAny { seguroTodoRiesgo.costoPara(taxiNuevoConInfra) }
        shouldThrowAny { seguroTodoRiesgo.costoPara(taxiNuevoSinInfra) }
      }
    }
    describe("Requerimiento 3 y 4: contratar un seguro si puede y saber el costo total de los seguros"){
      it("Para un auto"){
        autoViejo.contratar(seguroTerceros)
        autoNuevo.contratar(seguroTerceros)
        autoViejo.costoTotalDeSegurosContratados().shouldBe(500)
        autoNuevo.costoTotalDeSegurosContratados().shouldBe(1000)
        autoViejo.contratar(seguroRoboHurto)
        autoNuevo.contratar(seguroRoboHurto)
        autoViejo.costoTotalDeSegurosContratados().shouldBe(3000)
        autoNuevo.costoTotalDeSegurosContratados().shouldBe(4000)
        autoViejo.contratar(seguroTodoRiesgo)
        autoNuevo.contratar(seguroTodoRiesgo)
        autoViejo.costoTotalDeSegurosContratados().shouldBe(6500)
        autoNuevo.costoTotalDeSegurosContratados().shouldBe(11000)
      }
      it("Para un camion"){
        camionViejo.contratar(seguroTerceros)
        camionNuevo.contratar(seguroTerceros)
        camionViejo.costoTotalDeSegurosContratados().shouldBe(4000)
        camionNuevo.costoTotalDeSegurosContratados().shouldBe(7500)
        shouldThrowAny { camionViejo.contratar(seguroRoboHurto) }
        camionNuevo.contratar(seguroRoboHurto)
        camionNuevo.costoTotalDeSegurosContratados().shouldBe(32500)
        shouldThrowAny { camionViejo.contratar(seguroTodoRiesgo) }
        shouldThrowAny { camionNuevo.contratar(seguroTodoRiesgo) }
      }
      it("Para un taxi"){
        taxiViejoConInfra.contratar(seguroTerceros)
        taxiViejoSinInfra.contratar(seguroTerceros)
        taxiNuevoConInfra.contratar(seguroTerceros)
        taxiNuevoSinInfra.contratar(seguroTerceros)
        taxiViejoConInfra.costoTotalDeSegurosContratados().shouldBe(2600)
        taxiViejoSinInfra.costoTotalDeSegurosContratados().shouldBe(1800)
        taxiNuevoConInfra.costoTotalDeSegurosContratados().shouldBe(3400)
        taxiNuevoSinInfra.costoTotalDeSegurosContratados().shouldBe(3000)
        shouldThrowAny { taxiViejoConInfra.contratar(seguroRoboHurto) }
        shouldThrowAny { taxiViejoSinInfra.contratar(seguroRoboHurto) }
        taxiNuevoConInfra.contratar(seguroRoboHurto)
        taxiNuevoSinInfra.contratar(seguroRoboHurto)
        taxiNuevoConInfra.costoTotalDeSegurosContratados().shouldBe(9400)
        taxiNuevoSinInfra.costoTotalDeSegurosContratados().shouldBe(10500)
        shouldThrowAny { taxiViejoConInfra.contratar(seguroTodoRiesgo) }
        shouldThrowAny { taxiViejoSinInfra.contratar(seguroTodoRiesgo) }
        shouldThrowAny { taxiNuevoConInfra.contratar(seguroTodoRiesgo) }
        shouldThrowAny { taxiNuevoSinInfra.contratar(seguroTodoRiesgo) }
      }
    }
  }
})
