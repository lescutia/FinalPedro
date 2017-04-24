int main()
{
   int n;
   int c;
   int d;
    cout<<"enter a number\n";
    cin>>n;
   if ( n == 2 ){
      cout<<"Prime number.\n";
   }
   else
   {
    c=2;
       while (  c <= n - 1 )
       {
          d=modulo(n,c);
           if ( d== 0 ){
              break;
           }
           c++;
       }
       if ( c != n ){
          cout<<"Not prime.\n";
       }
       else{
          cout<<"Prime number.\n";
       }
   }
   return 0;
}