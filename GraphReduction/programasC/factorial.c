int main()
{
    int number;
    float factorial;

    cout<<"Enter an integer: \n";
    cin>>number;

    factorial = 1;

    while (number > 0)
    {
        factorial = factorial * number;  
        number--;
    }

    cout<<"Factorial= \n";
    cout<<factorial;

    return 0;
}