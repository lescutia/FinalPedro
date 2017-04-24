int main()
{
   int n;
   int first;
   int second; 
   int next;
   int c;
   first=0;
   second=1;
   cout<<"enter the number\n";
   cin>>n;
   while(c<n){
      if ( c <= 1 ){
         next = c;
      }
      else
      {
         next = first + second;
         first = second;
         second = next;
      }
      cout<<next;
      cout<<"\n";
      c++;
   }

 
   return 0;
}