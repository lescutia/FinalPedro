void imprimir(int n){
	cout<<"imprime\n";
	cout<<n;
}
int main(){
	int a;
	int i;
	cout<<"cuantas veces necesitas imprimir?";
	cin>>a;
	if(a==0){
		cout<<"no se imprimirá nada";
	}
	else{
		while(i<a){
			imprimir(a);
			i++;
		}
	}
	return 0;
}