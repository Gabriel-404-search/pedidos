create table pagamento(

    id bigint not null auto_increment unique,
    typePayment varchar(100) not null,
    paymentDate varchar(100) not null,
    document varchar(100) not null unique,
    typeStatus varchar(100) not null,
    amount varchar(100) not null,
    orderId varchar(100) not null unique,

    primary key(id)
);