using System;

namespace ConsoleGame
{
  class Game : SuperGame
  {

    // handles position change of the character based on keyboard input
    public new static void UpdatePosition(string key, out int x, out int y)
    {
      x = 0;
      y = 0;
      switch(key)
      {
        case "UpArrow":
        y--;
        break;
        case "DownArrow":
        y++;
        break;
        case "LeftArrow":
        x--;
        break;
        case "RightArrow":
        x++;
        break;
        default:
        break;
      }
    }

    // handles character change base on the input
    public new static char UpdateCursor(string key)
    {
      char symbol = '<';
      switch(key)
      {
        case "UpArrow":
        symbol = '^';
        break;
        case "DownArrow":
        symbol = 'v';
        break;
        case "LeftArrow":
        symbol = '<';
        break;
        case "RightArrow":
        symbol = '>';
        break;
      }
      return symbol;
    }

    // prevents character from moving out from the screen
    public new static int KeepInBounds(int coord, int max)
    {
      if(coord >= max){
        return max - 1;
      }else if (coord < 0){
        return 0;
      }
      return coord;
    }

    // compares character coordinates and fruit '@' coordinates
    public new static bool DidScore(int x, int y, int fruitX, int fruitY)
    {
      if(x == fruitX && y == fruitY){
        return true;
      }
      return false;
    }
  }
}
