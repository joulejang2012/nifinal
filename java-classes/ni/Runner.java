package ni;

import com.cycling74.max.*;
import java.util.ArrayList;
import util.Tuple;

public class Runner extends MaxObject {
  public final int kRefereeOutlet = 0;
  public final int kPatchControlOutlet = 1;

  public enum State {
    inPuzzle, finished
  };

  private State state;
  // Active puzzle.
  private AbstractPuzzle puzzle;

  public Runner (Atom[] args) {
    System.out.println("Initializing ni.Runner...");
    declareInlets(DataTypes.ALL);
    declareOutlets(DataTypes.ALL, DataTypes.ALL);

    if (args.length > 0) {
      if (args[0].getString().equals("left")) {
        Referee.getInstance().registerLeftRunner(this);
      } else if (args[0].getString().equals("right")) {
        Referee.getInstance().registerRightRunner(this);
      } else {
        System.err.println("Invalid arguments to ni.Runner.");
      }
    }

    state = State.finished;
  }

  /* Receives anything from Max inlet.
   */
  public void anything (String msg, Atom[] args) {
    if (state == State.finished) {
      // do nothing
    } else if (state == State.inPuzzle) {
      // forward input to puzzle
      boolean success = this.puzzle.receiveInput(msg);

      if (success) {
        puzzle = null;
        state = State.finished;
      }

      outlet(kRefereeOutlet, Atom.newAtom(success ? "success" : "failure"));
    }
  }

  // Returns true if successfully set puzzle, else false.
  public boolean receiveNextPuzzle (AbstractPuzzle puzzle) {
    if (state == State.finished) {
      this.puzzle = puzzle;
      return true;
    }
    // if `state` is not finished, can't set puzzle
    return false;
  }
}