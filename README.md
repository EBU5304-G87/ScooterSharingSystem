# ScooterSharingSystem
Coursework for EBU5304 By Group 87
## Run
1. Unzip `release-universal.zip`
2. In command line, `cd` to the root directory
3. - For Windows users, type `launch-windows.bat` and press enter;
   - For macOS users, type `./launch-mac.sh` and press enter;
   - For GNU/Linux users, type `./launch-linux.sh` and press enter.
   - **ATTENTION: THE PATH TO THE ROOT DIRECTORY MUST NOT CONTAIN ANY SPACE OR ANY NON-ASCII CHARACTER.**
   
## Usage
### Station
- The upper part is the simulation of the stations, while each tab represents one station.
- In each station, there are eight slots and an LCD screen used to output useful information. Besides, there is an RFID card reader, where users can swipe their card and start the borrow/return process.
- In our GUI, there are three indicators in each slot. `Available` shows if the slot has a scooter docked in; `Lock` shows if the slot is locked; `Light` shows if the light is on.
- The upper right part consists of three component. The upper text is representing the LCD screen, and the input box and the button simulate the function of the RFID reader. You can input the user ID and click the button to start the borrow/return process.
- Borrow and return: When initiating the process, the system will judge whether you have a scooter. If you have one, it will start the return process; otherwise, it will start the borrowing process.
- Then, the system will check if your ID is valid and if you need to pay for the fine. If any of the checks failed, the process will be terminated and there will be a message shows on the LCD screen.
- If all the checks have passed, the system will try to unlock a slot. If the station is full/empty(for return/borrow), the process will be terminated and it will show a message on the LCD screen.
- If a slot is unlocked, you'll have 60 seconds to take/dock the scooter. The LCD screen will tell you which slot to use, and the light on the slot will flash. 2 seconds later, the LCD screen will show the countdown.
- The user can now click `Take/Dock` to perform the action.
- If the user didn't do anything in 60 seconds, the slot will automatically lock again.
### Administration
- The below part is the administration interface.
- `List All Users` and `List Violated Users` will show the result in the left panel; `Generate Report` will show the result on the right panel.
- When clicking `Register`, it will open a new window, asking you to provide information: ID, name and email address. Notice: ID needs to be 9 digits, and email needs to be in a validating form. Besides, only data in `resources/SchoolUsers.json` can register.
- Input your ID in the input box and click `Generate Report`, it will generate your weekly usage in the right panel.
- If you've listed "all violated users", after selecting one of them, you can click `Change State` to change this user's violation state, assuming they've paid the fine.
