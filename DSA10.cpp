#include <iostream>
#include <fstream>
#include <conio.h>
using namespace std;

class File {
public:
    char name[20];
    int rno;

    void accept() {
        cout << "Enter the name : ";
        cin >> name;
        cout << "Enter roll no :";
        cin >> rno;
    }

    void display() {
        cout << "\nName : " << name;
        cout << "\nRoll no :" << rno;
    }

    void search(int srno) {
        fstream f;
        f.open("ABHI.txt", ios::in);
        bool found = false;
        File record;
        while (f.read((char*)&record, sizeof(record)) && !found) {
            if (record.rno == srno) {
                record.display();
                found = true;
            }
        }
        if (!found) {
            cout << "Record not found.\n";
        }
        f.close();
    }
};


int main() {
    // clrscr();
File o[10];
    fstream f;
    int i, n, choice, srno;
    while (true) {
        cout << "\n\n---------MENU----------" << endl;
        cout << "1 - Insert Data" << endl;
        cout << "2 - Display All" << endl;
        cout << "3 - Search" << endl;
        cout << "4 - Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                f.open("ABHI.txt", ios::out);
                cout << "How many records you want : ";
                cin >> n;
                for (i = 0; i < n; i++) {
                    o[i].accept();
                    f.write((char*)&o[i], sizeof(o[i]));
                }
                f.close();
                break;
            case 2:
                f.open("ABHI.txt", ios::in);
                for (i = 0; i < n; i++) {
                    f.read((char*)&o[i], sizeof(o[i]));
                    o[i].display();
                }
                f.close();
                break;
            case 3:
                cout << "Enter roll no to search : ";
                cin >> srno;
                File fileInstance;
                fileInstance.search(srno);
                break;
            case 4:
                cout << "Thank you for using the program." << endl;
                return 0;
            default:
                cout << "Invalid choice. Please select from the menu." << endl;
        }
    }
}
