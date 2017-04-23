int multiplicar(int a,int b){
	return a*b;
}
void sumar(int a,int b){
	return a+b;
}
void restar(int a,int b){
	return a-b;
}
int main(){
	int opcion;
	while(opcion!=-1){
		cout<<"que quieres hacer";
		cin>>opcion;

		cout<<"numero a";
		cin>>a;
		cout<<"numero b";
		cin>>b;
		if(opcion==0){
			cout<<multiplicar(a,b);
		}
		if(opcion==1){
			cout<<sumar(a,b);
		}
		if(opcion==3){
			cout<<restar(a,b);
		}
	}
	return 0;
}