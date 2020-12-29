<template>
  <div id="app">
    <div id="Caculator" class="grid">
      <div class="screen">
        <!--numbers and sign show here-->
        <div id="writingOperation">{{ writinglist }}</div>
        <div id="result">{{ result }}</div>
      </div>
      <div>
        <button
          id="mode"
          class="btnOperator"
          @click="
            operaterClicked = true;
            append('%');
          "
        >
          %
        </button>
        <button
          id="fraction"
          class="btnOperator"
          @click="
            operaterClicked = true;
            singleOperations('fraction'); 
          "
        >
          1/x
        </button>
        <button id="clear" class="btnOperator" @click="clear()">C</button>
        <button id="clearAll" class="btnOperator" @click="clear()">CE</button>
      </div>
      <div>
        <button
          id="power2"
          class="btnOperator"
          @click="
            operaterClicked = true;
            singleOperations('power'); 
          "
        >
          x<sup style="font-size: 10px">2</sup>
        </button>
        <button id="sign" class="btnOperator" 
        @click="
        operaterClicked = true;
        singleOperations('toggle'); 
        "
        >
          -/+
        </button>
        <button
          id="squareRoot"
          class="btnOperator"
          @click="
            operaterClicked = true;
            singleOperations('root');   
          "

        >
          &#8730;
        </button>
        <button
          id="divide"
          class="btnOperator"
          @click="
            operaterClicked = true;
            append('/');
          "
        >
          /
        </button>
      </div>
      <div>
        <button id="num7" class="btn" @click="append('7')">7</button>
        <button id="num8" class="btn" @click="append('8')">8</button>
        <button id="num9" class="btn" @click="append('9')">9</button>
        <button
          id="multiply"
          class="btnOperator"
          @click="
            operaterClicked = true;
            append('*');
          "
        >
          *
        </button>
      </div>
      <div>
        <button id="num4" class="btn" @click="append('4')">4</button>
        <button id="num5" class="btn" @click="append('5')">5</button>
        <button id="num6" class="btn" @click="append('6')">6</button>
        <button
          id="subtract"
          class="btnOperator"
          @click="
            operaterClicked = true;
            append('-');
          "
        >
          -
        </button>
      </div>
      <div>
        <button id="num1" class="btn" @click="append('1')">1</button>
        <button id="num2" class="btn" @click="append('2')">2</button>
        <button id="num3" class="btn" @click="append('3')">3</button>
        <button
          id="add"
          class="btnOperator"
          @click="
            operaterClicked = true;
            append('+');
          "
        >
          +
        </button>
      </div>
      <div>
        <button id="zero" class="btn" @click="append('0')">0</button>
        <button id="dot" class="btn" @click="append('.')">.</button>
        <button id="equal" class="btnOperator" @click="equal()">=</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      firstNum: "",
      secondNum: "",
      operator: "",
      writtenOperator:"",
      writinglist: "",
      result: "",
      operaterClicked: false,
      operatorClickedBefore: false,
    };
  },
  methods: {
    chooseOperator(number){
       if(number=="+"){
         this.writtenOperator="pulse";
       }else if(number=="-"){
         this.writtenOperator="subtract";
       }else if(number=="*"){
         this.writtenOperator="multi";
       }else if(number=="/"){
         this.writtenOperator="divide";
       }else if(number=="%"){
         this.writtenOperator="mode";
       }
    },
    append(number) {
      if (this.writinglist == "" && this.operaterClicked == true) {
        this.operaterClicked = false;
      } else if (
        this.operaterClicked == false &&
        this.operatorClickedBefore == false
      ) {
        this.firstNum += number + "";
        this.writinglist += number;
      } else if (
        this.operaterClicked == true &&
        this.operatorClickedBefore == false
      ) {
        this.operator = number + "";
        this.chooseOperator(number);
        this.writinglist += number + "";
        this.operatorClickedBefore = true;
        this.operaterClicked = false;
      } else if (
        this.operaterClicked == false &&
        this.operatorClickedBefore == true
      ) {
        this.secondNum += number + "";
        this.writinglist += number + "";
      } else if (
        this.writinglist[this.writinglist.length-1]!=this.operator &&
        this.operatorClickedBefore == true
      ) {
        this.contiOperations(number);
        this.chooseOperator(number);
      }
      this.operaterClicked=false;
    },
    clear() {
      this.writinglist = "";
      this.result = "";
      this.operaterClicked = false;
      this.firstNum = "";
      this.secondNum = "";
      this.operator = "";
      this.operatorClickedBefore = false;
      this.writtenOperator="";
    },
    
    async equal() {
      let respone=await fetch(
        "http://localhost:8085/" +
          this.firstNum +
          "/" +
          this.secondNum +
          "/" +
          this.writtenOperator,
        { method: "get" }
      )
       // .then((response) =>  response.json())
        //.then(data => { 
        let data= await respone.json(); 
        this.result=await data+"";
        return this.result;
    },
    async contiOperations(number){
        let temp=await this.equal();
        this.firstNum=temp;
        this.secondNum = "";
        this.operator = number +"";
        this.writinglist=this.firstNum+"";
        this.writinglist+=number+"";
        this.operaterClicked = false;
        this.operatorClickedBefore = true;
     },
     async singleOperations(operation){
       var temp;
       if(this.result==""){
         temp=this.firstNum;
       }else{
         temp=this.result;
       }
        let respone=await fetch(
         "http://localhost:8085/" +
          temp +
          "/" +
          operation,
         { method: "get" }
        )
        let data= await respone.json(); 
        this.result=await data+"";
        this.firstNum=await this.result+"";
        this.secondNum = "";
        this.writinglist=this.firstNum+"";
        this.operaterClicked = false;
        this.operatorClickedBefore = false;

     }
  },
}
</script>

<style>
@import url("https://fonts.googleapis.com/css?family=Poppins:300,500&display=swap");

template {
  width: 100%;
  height: 200%;
  padding: 0;
  margin: 0;
}
#app {
  width: 100%;
  height: 200%;
  min-height: 600px;
  background-image: linear-gradient(to bottom, #038fa1, #023e6e);
  justify-content: center;
  align-items: center;
  display: flex;
}
#Caculator {
  display: grid;
  width: 85%;
  max-width: 250px;
  height: 375px;
  background-image: linear-gradient(to bottom, #004d59, #012024);
  padding: 10px;
  border-radius: 10px;
  box-shadow: 0 25px 35px -15px rgba(3, 28, 31, 0.85);
  text-align: center;
  /*flex*/
  justify-content: space-evenly;
  align-content: space-between;
}
.screen {
  width: 250px;
  height: 100px;
  background-color: #b3ecff;
  text-align: right;
  font-size: 20px;
  word-wrap: break-word;
}
.btn,
.btnOperator {
  width: 50px;
  height: 30px;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  text-align: center;
  text-decoration: none;
  outline: none;
  color: #484848;
  background-color: #f4faff;
  border-radius: 5px;
  justify-content: space-evenly;
  align-content: space-between;
  font-size: 20px;
}
#zero {
  width: 100px;
}
.btnOperator {
  background-color: #d9efff;
  color: #3fa9fc;
}
</style>
