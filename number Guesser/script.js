let humanScore = 0;
let computerScore = 0;
let currentRoundNumber = 1;

// Write your code below:
const generateTarget = () => {
  return Math.floor(Math.random() * 10);
}

const compareGuesses = (humanGuess, computerGuess, secretTarget) => {
  if(Math.abs(secretTarget - humanGuess) < Math.abs(secretTarget - computerGuess) || Math.abs(secretTarget - humanGuess) === Math.abs(secretTarget - computerGuess)){
    return true;
  }else {
    return false;
  }
}

const updateScore = (winner) => {
  winner = winner.toLowerCase();
  if(winner === "human"){
    humanScore++;
  }else if(winner === "computer"){
    computerScore++;
  }
}

const advanceRound = () => {
  currentRoundNumber++;
}
