# Usage
## Station
- The upper part is the simulation of the stations, while each tab represents one station.
- In each station, there are eight slots and an LCD screen used to output useful information. Besides, there is an RFID card reader, where users can swipe their card and start the borrow/return process.
- In our GUI, there are three indicators in each slot. `Available` shows if the slot has a scooter docked in; `Lock` shows if the slot is locked; `Light` shows if the light is on.
- The upper right part consists of three component. The upper text is representing the LCD screen, and the input box and the button simulate the function of the RFID reader. The user can input the ID and click the button to start the borrow/return process.
- Borrow and return: When initiating the process, the system will judge whether the user have a scooter. If the user have one, it will start the return process; otherwise, it will start the borrowing process.
- Then, the system will check if the user's ID is valid and if the user need to pay for the fine. If any of the checks failed, the process will be terminated and there will be a message shows on the LCD screen.
- If all the checks have passed, the system will try to unlock a slot. If the station is full/empty(for return/borrow), the process will be terminated and it will show a message on the LCD screen.
- If a slot is unlocked, the user will have 60 seconds to take/dock the scooter. The LCD screen will tell the user which slot to use, and the light on the slot will flash. 2 seconds later, the LCD screen will show the countdown.
- The user can now click `Take/Dock` to perform the action.
- If the user didn't do anything in 60 seconds, the slot will automatically lock again.
## Administration
- The below part is the administration interface.
- `List All Users` and `List Violated Users` will show the result in the left panel; `Generate Report` will show the result on the right panel.
- When clicking `Register`, it will open a new window, asking the user to provide information: ID, name and email address. Notice: ID needs to be 9 digits, and email needs to be in a validating form. Besides, only data in `resources/SchoolUsers.json` can register.
- Input ID in the input box and click `Generate Report`, it will generate the user's weekly usage in the right panel.
- If you've listed "all violated users", after selecting one of them, you can click `Change State` to change this user's violation state, assuming they've paid the fine.