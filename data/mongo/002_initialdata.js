db = db.getSiblingDB('btgpactual');

db.createCollection('pedidos');

db.pedidos.insertMany([
 {
   _id: '1001',
   codigoCliente:'1',
   itens: [
       {
           produto: "lápis",
           quantidade: 100,
           preco: 1.10
       },
       {
           produto: "caderno",
           quantidade: 10,
           preco: 5.00
       }
   ]
  },
  {
    _id: '1002',
    codigoCliente: '2',
    itens: [
        {
            produto: "lápis",
            quantidade: 50,
            preco: 1.10
        },
        {
            produto: "borracha",
            quantidade: 50,
            preco: 1.00
        }
    ]
  },
  {
    _id: '1003',
    codigoCliente: '1',
    itens: [
        {
            produto: "caneta",
            quantidade: 200,
            preco: 2.10
        },
        {
            produto: "caderno",
            quantidade: 25,
            preco: 5.00
        }
    ]
  }
]);

db.createCollection('clientes');

db.clientes.insertMany([
  {
    _id:'1',
    nomeCliente:'Escola',
    pedidos: [
      {
        id:'1001',
        precoDoPedido:'160.0'
      },
      {
        id:'1003',
        precoDoPedido:'545.0'
      }
    ]
  },
  {
    _id:'2',
    nomeCliente:'Universidade',
    pedidos: [
      {
        id:'1002',
        precoDoPedido:'105.0'
      }
    ]
  },

]);
