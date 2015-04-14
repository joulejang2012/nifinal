package ni;

import java.util.*;
import util.Tuple;

public class PuzzleSequence {
  // TODO:
  // private ArrayList<PuzzleEvent> puzzles;
  // PuzzleEvent = Tuple<AbstractPuzzle, AbstractPuzzle> | TransitionVideo
  private ArrayList<Tuple<AbstractPuzzle, AbstractPuzzle>> puzzles;
  private int index;

  public PuzzleSequence () {
	System.out.println("Constructing puzzles...");
    this.puzzles = new ArrayList<Tuple<AbstractPuzzle, AbstractPuzzle>>(); 
    
    // this.puzzles.add(puzzlePair(new RhythmPuzzle("123.mov"), new RhythmPuzzle("123.mov")));
  	// this.puzzles.add(puzzlePair(new AnsMayVaryOnewayPuzzle("123.mov"), new AnsMayVaryOnewayPuzzle("123.mov")));
    // this.puzzles.add(puzzlePair(new TestMultipleChoicePuzzle(), new TestMultipleChoicePuzzle()));
    this.puzzles.add(puzzlePair(GenericMultipleChoicePuzzle.make("What do I do?", 
                                                                 new BlueButton(), 
                                                                 "correct",
                                                                 "incorrect",
                                                                 "also wrong"),
                                GenericMultipleChoicePuzzle.make("What do I do?", 
                                                                 new BlueButton(), 
                                                                 "correct",
                                                                 "incorrect",
                                                                 "also wrong")));
    // this.puzzles.add( transition video );
    index = 0;
  }


  // Sets the current puzzle to the next puzzle, and returns that puzzle, or null if at end of puzzle list.
  public Tuple<AbstractPuzzle, AbstractPuzzle> next() {
    index++;
    return index >= puzzles.size() ? null : puzzles.get(index);
  } 

  // Returns the current puzzles.
  public Tuple<AbstractPuzzle, AbstractPuzzle> current() {
    return puzzles.get(index);
  }

  public void reset () {
    index = 0;
  }

  private Tuple<AbstractPuzzle, AbstractPuzzle> puzzlePair(AbstractPuzzle left, AbstractPuzzle right) {
    return new Tuple<AbstractPuzzle, AbstractPuzzle>(left, right);
  }
}