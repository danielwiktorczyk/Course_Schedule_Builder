// import React, { Component } from 'react';
// import '../css/App.css';
// import {withRouter} from "react-router-dom";
//
// class PlayGame extends Component {
//
//     constructor(props) {
//         super(props);
//         this.handleLoad = this.handleLoad.bind(this);
//     }
//
//     componentDidMount() {
//         window.addEventListener('load', this.handleLoad);
//     }
//
//     handleLoad() {
//         loadGame() //  $ is available here
//     }
//
//     render(){
//         return (
//             <div>
//                 <canvas id="gameCanvas" width="800" height="600"></canvas>
//                 <script>
//                     const WINNING_SCORE=3, CANVAS_WIDTH=800, CANVAS_HEIGHT=600, PADDLE_HEIGHT = 100, PADDLE_THICKNESS = 10;
//                     var canvas, CanvasContext, playerScore=0, paddleScore=0, playerY=0, paddleY=0, paddleSpeed=3, showWin=false;
//                     var ballX=(CANVAS_WIDTH/2), ballY=(CANVAS_HEIGHT/2), ballSpeedX=5*1.3, ballSpeedY=3;
//
//                     loadGame(){
//                     var fps= 60;
//                     canvas = document.getElementById('gameCanvas');
//                     canvasContext = canvas.getContext('2d');
//                     setInterval(function(){moveEverything();drawEverything();}, 1000/fps);
//                     canvas.addEventListener('mousemove', function(evt){
//                     var mousePos = calculateMousePos(evt);
//                     playerY=mousePos.y-(PADDLE_HEIGHT/2);
//                     //paddleY=mousePos.y-(PADDLE_HEIGHT/2)
//                 });
//                     canvas.addEventListener('mousedown', mouseClick);
//                 }
//                     mouseClick(evt){
//                     if(showWin){
//                     paddleScore=0;
//                     playerScore=0;
//                     showWin=false;
//                 }}
//                     computerMovement(){
//                     var paddleYCenter=paddleY+(PADDLE_HEIGHT/2);
//                     if(paddleYCenter<ballY-35){
//                     paddleY+=9;
//                 }else if(paddleYCenter>ballY+35){
//                     paddleY-=9;          }
//                 }
//                     ballReset(){
//                     if(playerScore>=WINNING_SCORE || paddleScore>=WINNING_SCORE){
//                     showWin=true;
//                 }
//                     ballX=CANVAS_WIDTH/2;
//                     ballY=(canvas.height/2);
//                     ballSpeedX=-ballSpeedX;
//                     if(ballSpeedY<0){
//                     ballSpeedY=4;}
//                     else if (ballSpeedY>0){
//                     ballSpeedY=-4;}
//                     //ballSpeedY=4;
//                 }
//                     moveEverything(){
//                     if(showWin){
//                     return; }
//                     computerMovement();
//                     ballX+=ballSpeedX;
//                     ballY+=ballSpeedY;
//                     if (ballX>CANVAS_WIDTH){
//                     if (ballY>paddleY && ballY<paddleY+PADDLE_HEIGHT){
//                     ballSpeedX=-ballSpeedX;
//                     var deltaY=ballY-(paddleY+PADDLE_HEIGHT/2);
//                     ballSpeedY=deltaY*0.30;
//                 }  else{
//                     playerScore++; //before reset for game end
//                     ballReset();
//                 }
//                 }
//                     if (ballX<0){
//                     if (ballY>playerY && ballY<playerY+PADDLE_HEIGHT){
//                     ballSpeedX=-ballSpeedX;
//                     var deltaY=ballY-(playerY+PADDLE_HEIGHT/2);
//                     ballSpeedY=deltaY*0.30;
//                 }  else{
//                     paddleScore++;
//                     ballReset();
//                 }
//                 }
//                     if (ballY>canvas.height){
//                     ballSpeedY=-ballSpeedY;
//                 }
//                     if (ballY<0){
//                     ballSpeedY=-ballSpeedY;
//
//                 }
//                     if (paddleY>canvas.height-5){
//                     paddleSpeed=-paddleSpeed;
//                 }
//                     if (paddleY<0){
//                     paddleSpeed=-paddleSpeed;}
//                 }
//                     calculateMousePos(evt){
//                     var rect=canvas.getBoundingClientRect();
//                     var root = document.documentElement;
//                     var mouseX=evt.clientX - rect.left - root.scrollLeft;
//                     var mouseY=evt.clientY - rect.top - root.scrollTop;
//                     return {x:mouseX, y:mouseY};
//                 }
//                     drawEverything(){
//                     colorRect(0,0,canvas.width,canvas.height,'black');				//draws area of game
//
//                     if(showWin){
//                     canvasContext.fillStyle='red';
//                     canvasContext.font="50px Verdana";
//                     if(playerScore>=WINNING_SCORE){
//                     canvasContext.fillText("You Won",200,CANVAS_HEIGHT/2);
//                 }
//                     else if (paddleScore>=WINNING_SCORE){
//                     canvasContext.fillText("You Lost",200,CANVAS_HEIGHT/2);
//                 }
//                     canvasContext.fillText("click to continue",200,CANVAS_HEIGHT/2+100);
//
//                     return;}
//                     colorRect(0,playerY,PADDLE_THICKNESS,PADDLE_HEIGHT,'white');			//draws player of the game
//                     colorRect(canvas.width-PADDLE_THICKNESS,paddleY,PADDLE_THICKNESS,100,'white');//draws the opponent
//                     colorCircle	(ballX, ballY, 7, 'red');				//draws the ball of game
//                     canvasContext.fillText(playerScore,100,100);
//                     canvasContext.fillText(paddleScore,CANVAS_WIDTH-100,100);
//                 }
//                     colorCircle(centerX, centerY, radius, drawColor){
//                     canvasContext.fillStyle=drawColor;
//                     canvasContext.beginPath();
//                     canvasContext.arc(centerX, centerY, radius, 0, Math.PI*2, true);
//                     canvasContext.fill();
//                 }
//                     colorRect(leftX, topY, width, height, drawColor){
//                     canvasContext.fillStyle=drawColor;
//                     canvasContext.fillRect(leftX, topY, width, height, drawColor);
//                 }
//                 </script>
//
//
//             </div>
//
//         );
//     }
// }
// export default withRouter(PlayGame);
//
