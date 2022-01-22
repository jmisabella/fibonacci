package models.behaviors.fibonacci

import org.scalatest.flatspec.AnyFlatSpec

class FibonacciSpec extends AnyFlatSpec {
  private case object fibonacciByRecursion extends FibonacciByRecursion
  private case object fibonacciByList extends FibonacciByList

  "FibonacciByRecursion" should "get fib(0)" in {
    val value = 0
    val expected = 0
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 0 as empty list" in {
    val value = 0
    val expected: List[BigInt] = Nil
    val actual = fibonacciByRecursion.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(1)" in {
    val value = 1
    val expected = 1
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 1" in {
    val value = 1
    val expected: List[BigInt] = List(0).map(BigInt(_))
    val actual = fibonacciByRecursion.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(2)" in {
    val value = 2
    val expected = 1
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 2" in {
    val value = 2
    val expected: List[BigInt] = List(0, 1).map(BigInt(_))
    val actual = fibonacciByRecursion.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(3)" in {
    val value = 3
    val expected = 2
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib sequence size 3" in {
    val value = 3
    val expected: List[BigInt] = List(0, 1, 1).map(BigInt(_))
    val actual = fibonacciByRecursion.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(4)" in {
    val value = 4
    val expected = 3
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib sequence size 4" in {
    val value = 4
    val expected: List[BigInt] = List(0, 1, 1, 2).map(BigInt(_))
    val actual = fibonacciByRecursion.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(5)" in {
    val value = 5
    val expected = 5
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 5" in {
    val value = 5
    val expected: List[BigInt] = List(0, 1, 1, 2, 3).map(BigInt(_))
    val actual = fibonacciByRecursion.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(6)" in {
    val value = 6
    val expected = 8
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib sequence size 6" in {
    val value = 6
    val expected: List[BigInt] = List(0, 1, 1, 2, 3, 5).map(BigInt(_))
    val actual = fibonacciByRecursion.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(7)" in {
    val value = 7
    val expected = 13
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(8)" in {
    val value = 8
    val expected = 21
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(84)" in {
    val value = 84
    val expected = BigInt("160500643816367088")
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(360)" in {
    val value = 360
    val expected = BigInt("769246427201094785080787978422393713094534885688979999504447628313150135520")
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(500)" in {
    val value = 500
    val expected = BigInt("139423224561697880139724382870407283950070256587697307264108962948325571622863290691557658876222521294125")
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(999)" in {
    val value = 999
    val expected = BigInt("26863810024485359386146727202142923967616609318986952340123175997617981700247881689338369654483356564191827856161443356312976673642210350324634850410377680367334151172899169723197082763985615764450078474174626")
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(10000)" in {
    val value = 10000
    val expected = BigInt("33644764876431783266621612005107543310302148460680063906564769974680081442166662368155595513633734025582065332680836159373734790483865268263040892463056431887354544369559827491606602099884183933864652731300088830269235673613135117579297437854413752130520504347701602264758318906527890855154366159582987279682987510631200575428783453215515103870818298969791613127856265033195487140214287532698187962046936097879900350962302291026368131493195275630227837628441540360584402572114334961180023091208287046088923962328835461505776583271252546093591128203925285393434620904245248929403901706233888991085841065183173360437470737908552631764325733993712871937587746897479926305837065742830161637408969178426378624212835258112820516370298089332099905707920064367426202389783111470054074998459250360633560933883831923386783056136435351892133279732908133732642652633989763922723407882928177953580570993691049175470808931841056146322338217465637321248226383092103297701648054726243842374862411453093812206564914032751086643394517512161526545361333111314042436854805106765843493523836959653428071768775328348234345557366719731392746273629108210679280784718035329131176778924659089938635459327894523777674406192240337638674004021330343297496902028328145933418826817683893072003634795623117103101291953169794607632737589253530772552375943788434504067715555779056450443016640119462580972216729758615026968443146952034614932291105970676243268515992834709891284706740862008587135016260312071903172086094081298321581077282076353186624611278245537208532365305775956430072517744315051539600905168603220349163222640885248852433158051534849622434848299380905070483482449327453732624567755879089187190803662058009594743150052402532709746995318770724376825907419939632265984147498193609285223945039707165443156421328157688908058783183404917434556270520223564846495196112460268313970975069382648706613264507665074611512677522748621598642530711298441182622661057163515069260029861704945425047491378115154139941550671256271197133252763631939606902895650288268608362241082050562430701794976171121233066073310059947366875")
    val actual = fibonacciByRecursion.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  

  "FibonacciByList" should "get fib(0)" in {
    val value = 0
    val expected = 0
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib sequence size 0 as an empty list" in {
    val value = 0
    val expected: List[BigInt] = Nil
    val actual = fibonacciByList.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(1)" in {
    val value = 1
    val expected = 1
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 1" in {
    val value = 1
    val expected: List[BigInt] = List(0).map(BigInt(_))
    val actual = fibonacciByList.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(2)" in {
    val value = 2
    val expected = 1
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 2" in {
    val value = 2
    val expected: List[BigInt] = List(0, 1).map(BigInt(_))
    val actual = fibonacciByList.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(3)" in {
    val value = 3
    val expected = 2
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 3" in {
    val value = 3
    val expected: List[BigInt] = List(0, 1, 1).map(BigInt(_))
    val actual = fibonacciByList.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(4)" in {
    val value = 4
    val expected = 3
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib sequence size 4" in {
    val value = 4
    val expected: List[BigInt] = List(0, 1, 1, 2).map(BigInt(_))
    val actual = fibonacciByList.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(5)" in {
    val value = 5
    val expected = 5
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 5" in {
    val value = 5
    val expected: List[BigInt] = List(0, 1, 1, 2, 3).map(BigInt(_))
    val actual = fibonacciByList.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(6)" in {
    val value = 6
    val expected = 8
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib sequence size 6" in {
    val value = 6
    val expected: List[BigInt] = List(0, 1, 1, 2, 3, 5).map(BigInt(_))
    val actual = fibonacciByList.fibonacciSequence(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(7)" in {
    val value = 7
    val expected = 13
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(8)" in {
    val value = 8
    val expected = 21
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(84)" in {
    val value = 84
    val expected = BigInt("160500643816367088")
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(360)" in {
    val value = 360
    val expected = BigInt("769246427201094785080787978422393713094534885688979999504447628313150135520")
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(500)" in {
    val value = 500
    val expected = BigInt("139423224561697880139724382870407283950070256587697307264108962948325571622863290691557658876222521294125")
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }
  
  it should "get fib(999)" in {
    val value = 999
    val expected = BigInt("26863810024485359386146727202142923967616609318986952340123175997617981700247881689338369654483356564191827856161443356312976673642210350324634850410377680367334151172899169723197082763985615764450078474174626")
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  it should "get fib(10000)" in {
    val value = 10000
    val expected = BigInt("33644764876431783266621612005107543310302148460680063906564769974680081442166662368155595513633734025582065332680836159373734790483865268263040892463056431887354544369559827491606602099884183933864652731300088830269235673613135117579297437854413752130520504347701602264758318906527890855154366159582987279682987510631200575428783453215515103870818298969791613127856265033195487140214287532698187962046936097879900350962302291026368131493195275630227837628441540360584402572114334961180023091208287046088923962328835461505776583271252546093591128203925285393434620904245248929403901706233888991085841065183173360437470737908552631764325733993712871937587746897479926305837065742830161637408969178426378624212835258112820516370298089332099905707920064367426202389783111470054074998459250360633560933883831923386783056136435351892133279732908133732642652633989763922723407882928177953580570993691049175470808931841056146322338217465637321248226383092103297701648054726243842374862411453093812206564914032751086643394517512161526545361333111314042436854805106765843493523836959653428071768775328348234345557366719731392746273629108210679280784718035329131176778924659089938635459327894523777674406192240337638674004021330343297496902028328145933418826817683893072003634795623117103101291953169794607632737589253530772552375943788434504067715555779056450443016640119462580972216729758615026968443146952034614932291105970676243268515992834709891284706740862008587135016260312071903172086094081298321581077282076353186624611278245537208532365305775956430072517744315051539600905168603220349163222640885248852433158051534849622434848299380905070483482449327453732624567755879089187190803662058009594743150052402532709746995318770724376825907419939632265984147498193609285223945039707165443156421328157688908058783183404917434556270520223564846495196112460268313970975069382648706613264507665074611512677522748621598642530711298441182622661057163515069260029861704945425047491378115154139941550671256271197133252763631939606902895650288268608362241082050562430701794976171121233066073310059947366875")
    val actual = fibonacciByList.fibonacciNumber(value)
    assert(expected == actual, s"Expected [$expected], actual [$actual]")
  }

  
}