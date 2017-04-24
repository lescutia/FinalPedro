int main()
{
    int i;
    float number;
    float sum;
    sum=0;
    i=1;
    while(i <= 10)
    {
        cout<<"enter a number ";
        cout<<i;
        cin>>number;
        if(number < 0)
        {
            continue;
        }

        sum = sum+number; 
        i++;
    }
    cout<<sum;   
    return 0;
}