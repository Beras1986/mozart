class Pokemon:
  # pokemon class constructor
  def __init__(self, name, level, health, max_health, pokemon_type, is_knocked_out):
    self.name = name
    self.level = level
    self.health = health
    self.max_health = max_health
    self.type = pokemon_type
    self.is_knocked_out = is_knocked_out
  
  # pokemon class represent method
  def __repr__(self):
    return "{} is {} level, {} type, now has {} health where {} is the maximum health. Is knocked out: {}\n".format(self.name, self.level, self.type, self.health, self.max_health, self.is_knocked_out)

  # decreases pokemons health by given value
  def decrease_health_by(self, amount):
    self.health -= amount
    if self.health < 0:
      self.health = 0
  
  # restores full heath of the pokemon
  def restore_health(self):
    self.health = self.max_health
    print("{} health has been restored\n".format(self.name))
  
  # knocks pokemon out
  def knock_out(self):
    self.is_knocked_out = True
    print("{} has been knocked out\n".format(self.name))
  
  # revives pokemon
  def stand_up(self):
    self.is_knocked_out = False
    print("{} stads up\n".format(self.name))
  
  # levels up, increases max health by 5 and restores health
  def level_up(self):
    self.level += 1
    self.max_health += 5
    self.restore_health()
    print("{} levels up\n".format(self.name))

  # attack method takes other pokemon as argument and deals damage to it
  def attack(self, other_pokemon):
    damage = self.level
    if self.type == "Fire" and other_pokemon.type == "Grass":
      damage *= 2
    elif self.type == "Grass" and other_pokemon.type == "Fire":
      damage /= 2
    other_pokemon.decrease_health_by(damage)
    print("{} pokemon suffered {} damage\n".format(other_pokemon.name, damage))


class Trainer:
  # constructor for Trainer class
  def __init__(self, pokemons_list, potions, current_pokemon, name):
    self.pokemons = pokemons_list # lsit of pokemons
    self.potions = potions # number of potions
    self.current_pokemon = current_pokemon # sctive pokemon
    self.name = name # trainers name

  # trainer class represent method
  def __repr__(self):
    return "{} has {} as currently active pokemon.\n".format(self.name, self.current_pokemon.name)
  
  # trainer uses potion on currently active pokemon to restore its health
  def use_potion(self):
    if self.potions > 0:
      self.current_pokemon.restore_health()
      self.potions -= 1
      print("{} used potion on {}".format(self.name, self.current_pokemon.name))

  # attack other trainer with curent pokemon
  def attack(self, other_trainer):
    self.current_pokemon.attack(other_trainer.current_pokemon)
    print("{} attacked {}\n".format(self.name, other_trainer.name))


  # switches betwen pokemons in the list
  def change_pokemon(self, new_pokemon):
    if new_pokemon < len(self.pokemons) and new_pokemon >= 0:
      if self.pokemons[new_pokemon].is_knocked_out:
        print("{} is knocked out, you can't use it now.".format(self.pokemons[new_pokemon].name))
      elif self.pokemons[new_pokemon] == self.current_pokemon:
        print("This pokemon is already selected")
      else:
        self.current_pokemon = self.pokemons[new_pokemon]
        print("{} is your new active pokemon".format(self.current_pokemon.name))

    
#
  # Uncomment for testing
#
# pokemon_1 = Pokemon("Azor", 10, 50, 50, "Fire", False)
# pokemon_2 = Pokemon("Micky", 5, 40, 40, "Grass", False)
# pokemon_3 = Pokemon("Melisa", 15, 80, 80, "Fire", False)
# pokemon_4 = Pokemon("Hannah", 1, 15, 15, "Grass", False)

# print(pokemon_1)

# trainer_1 = Trainer([pokemon_1, pokemon_2], 4, pokemon_2, "Johnny")
# trainer_2 = Trainer([pokemon_3, pokemon_4], 3, pokemon_3, "Anna")

# trainer_1.attack(trainer_2)
# trainer_2.attack(trainer_1)
