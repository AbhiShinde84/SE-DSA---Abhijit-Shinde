
#include<iostream>
#include<fstream>
#include<conio.h>
using namespace std;
class file
{
public :
char name[20];
int rno;
void accept()
{
 cout<<"Enter the name : ";
 cin>>name;
 cout<<"Enter roll no :";
 cin>>rno;
}
void display()
{
    cout<<"\nName : "<<name;
    cout<<"\nRoll no :"<<rno;

}
};


int main()
{   
    // clrscr();
    file o[10] ;
    fstream f;
    int i,n;
    f.open("ABHI.txt" , ios::out);
    cout<<"How many records you want : ";
    cin>>n;
    for(i=0;i<n;i++)
    {
	o[i].accept();
	f.write((char*)&o[i],sizeof(o[i]));
    }
    f.close();
    f.open("ABHI.txt",ios::in);
    for(i=0;i<n;i++)
    {
	f.read((char*)&o[i],sizeof(o[i]));
	o[i].display();
    }
    f.close();
    return 0;

}
