export interface Persona {
    id: string;
    name: string;
    website: string;
    logo: string;
}

export interface Artikle {
    id: string;
    title: string;
    date: string;
    url: string;
    owner: Persona;
}
