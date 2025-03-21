public class ButtonFactory {
   // Single instances for the two states
   private Button selectedButton;
   private Button normalButton;
   
   //Button factory method whihc returns the button based on the state
   //Either Selected or normal as in unselected

   //Selected button only created the the button is hoovered over
   public Button getButton(boolean selected) {
       if (selected) {
           if (selectedButton == null) {
               selectedButton = new Button(true);
           }
           return selectedButton;
      //Unselected button created when the button is not hoovered over
       } else {
           if (normalButton == null) {
               normalButton = new Button(false);
           }
           return normalButton;
       }
   }
}
