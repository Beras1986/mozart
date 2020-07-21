import random

money = 100

#Write your game of chance functions here

def flipp_coin_bet(guess, bet):
  if(bet > money):
    bet = money;
  num = random.randint(1, 2)
  if (num == 1 and guess == "Heads") or (num == 2 and guess == "Tails"):
    return bet
  else:
    return -bet
    print("You have lost {}".format(bet))

def chohan(guess, bet):
  if(bet > money):
    bet = money;
  dice_1 = random.randint(1, 6)
  dice_2 = random.randint(1, 6)
  result = dice_1 + dice_2
  if((result % 2 == 0 and guess == "Even") or (result % 2 != 0 and guess == "Odd")):
    return bet
  else: return -bet

# inlcuding joker car, each player has a deck of cards
def two_player_card_guess(bet):
  if(bet > money):
    bet = money;
  player_1_draw = random.randint(1, 13)
  player_2_draw = random.randint(1, 13)
  if(player_1_draw > player_2_draw):
    return bet
  elif(player_1_draw < player_2_draw):
    return -bet
  else:
    return 0

# player may guess color 1-10 and 19-28 odd red and even black
# 11-18 and 29-36 odd black and even red(wins bet*2) OR may simply guess even or odd where 0 always losses (wins bet)
# Player may also guess specific number (0 to 36), but will will only if that number will be the result of spin (wins bet * 5)
def roulette(guess, bet):
  if(bet > money):
    bet = money;
  num = random.randint(0, 36)
  if(guess == "Odd" or guess == "Even" or guess == "Red" or guess == "Black"):
    if((num in range(1, 11) or num in range(29, 37)) and guess == "Red"):
      return bet*2
    elif((num in range(19, 29) or num in range(11, 19)) and guess == "Black"):
      return bet*2
    elif(num != 0 and (num % 2 == 0 and guess == "Even") or (num % 2 != 0 and guess == "Odd")):
      return bet
    else:
      return -bet
  else:
    if(num == int(guess)):
      return bet*5
    else:
      return -bet



#Call your game of chance functions here
money += flipp_coin_bet("Head", 20)
money += chohan("Even", 20)
money += two_player_card_guess(20)
money += roulette("Black", 50)


print(money)
