export const userData = {
  username: "dsbfelipe",
  fullName: "Felipe dos Santos Bento",
  city: "Sorocaba",
  stateCode: "SP",
  email: "dsbfelipe@outlook.com",
  profilePicture:
    "https://avatars.githubusercontent.com/u/141741516?s=400&u=5d8f4fcf45a3df114e37b01b82973d17a3356763&v=4",
};

export const bills = {
  list: [
    {
      billName: "Praia",
      admin: "mariana",
      payUntil: "2024-10-25",
      createdAt: "2024-10-12",
      method: "Pix",
      totalValue: 105.0,
      participants: [
        {
          username: "dsbfelipe",
          valueToPay: 35.0,
          status: "paid",
        },
        {
          username: "llasantinelli",
          valueToPay: 35.0,
          status: "pending",
        },
        {
          username: "mariana",
          valueToPay: 35.0,
          status: "paid",
        },
      ],
    },
  ],
};

export const friends = {
  list: [
    {
      username: "llasantinelli",
      fullName: "Lauri Ellen Santinelli Biral",
      profilePicture:
        "https://media.licdn.com/dms/image/v2/D4D03AQELFhe5V4-HtA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1729015627002?e=1734566400&v=beta&t=kGzAmW11X5gqfHQHKeTnL8JEVEPWBJeCJ60GM8Q2E1k",
      email: "lauri@gmail.com",
    },
    {
      username: "mariana",
      fullName: "Mariana Parducci",
      profilePicture:
        "https://media.licdn.com/dms/image/v2/D4D03AQGbOZyFofuDnA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1673382953481?e=1734566400&v=beta&t=0Y9pdKkAXKB4WOgJ6o2BZJIQHUTdLjby0dU3UXqvt0M",
      email: "mariana@gmail.com",
    },
    {
      username: "jquaglia",
      fullName: "João Marcelo",
      profilePicture:
        "https://media.licdn.com/dms/image/v2/D5603AQHre4kgHH4fxA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1705510059331?e=1735171200&v=beta&t=cj6ld-zB0lXE58hM4L0lD34-hr18tX9lUoBlkYgOGJ4",
      email: "joão@gmail.com",
    },
  ],
};

export const paymentMethods = {
  Pix: {
    chave: "email@email.com",
  },
  "Transferência Bancária": {
    nomeTitular: "Felipe dos Santos Bento",
    numeroConta: "1234567890",
    agencia: "1234",
    tipoConta: "Conta Corrente",
    cpf: "123.456.789-00",
  },
  PayPal: {
    emailDestinatario: "contato@exemplo.com",
    nomeDestinatario: "Maria Oliveira",
  },
};

export const registeredUsers = {
  list: [
    {
      username: "llasantinelli",
      fullName: "Lauri Ellen Santinelli Biral",
      profilePicture:
        "https://media.licdn.com/dms/image/v2/D4D03AQELFhe5V4-HtA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1729015627002?e=1734566400&v=beta&t=kGzAmW11X5gqfHQHKeTnL8JEVEPWBJeCJ60GM8Q2E1k",
      email: "lauri@gmail.com",
      pix: "lauri@gmail.com",
    },
    {
      username: "dsbfelipe",
      fullName: "Felipe dos Santos Bento",
      profilePicture:
        "https://avatars.githubusercontent.com/u/141741516?s=400&u=5d8f4fcf45a3df114e37b01b82973d17a3356763&v=4",
      email: "dsbfelipe@outlook.com",
      pix: "(15)98810-7304",
    },
    {
      username: "mariana",
      fullName: "Mariana Parducci",
      profilePicture:
        "https://media.licdn.com/dms/image/v2/D4D03AQGbOZyFofuDnA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1673382953481?e=1734566400&v=beta&t=0Y9pdKkAXKB4WOgJ6o2BZJIQHUTdLjby0dU3UXqvt0M",
      email: "mariana@gmail.com",
      pix: "mariana@gmail.com",
    },
    {
      username: "jquaglia",
      fullName: "João Marcelo",
      profilePicture:
        "https://media.licdn.com/dms/image/v2/D5603AQHre4kgHH4fxA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1705510059331?e=1735171200&v=beta&t=cj6ld-zB0lXE58hM4L0lD34-hr18tX9lUoBlkYgOGJ4",
      email: "joão@gmail.com",
    },
    {
      username: "carolzinha",
      fullName: "Caroline Ramos",
      profilePicture:
        "https://media.licdn.com/dms/image/v2/C4E03AQFT5qlU9dPhNA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1623685539736?e=1737590400&v=beta&t=Z-OcoURgZICL6JiL2e2AIoXz3OPv9F-S7brpun5Prag",
      email: "carol@gmail.com",
    },
  ],
};

export const friendRequests = {
  list: [
    {
      sentBy: "carolzinha",
      status: "none",
      date: "2024-11-15",
    },
  ],
};
