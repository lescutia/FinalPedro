int main()
{
    int number1;
    int number2;
    cout<<"Enter two integers: ";
    cin>>number1;
    cin>>number2;

    if(number1 == number2)
    {
        cout<<"son iguales";
    }


    else {
        if (number1 > number2)
        {
            cout<<"number 1 es mayor";
        }

        else
        {
            cout<<"number 2 es mayor";
        }
    }

    return 0;
}