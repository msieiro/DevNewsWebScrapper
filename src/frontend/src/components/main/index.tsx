/* eslint-disable @typescript-eslint/no-explicit-any */
import { Artikle } from "@/interfaces";
import { API_URL, articlesAPI } from "@/lib/axios";
import { Container } from "@components";
import { FC, useEffect, useState } from "react";

export const Main: FC = () => {
    const [error, setError] = useState<any>(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [latestArticles, setLatestArticles] = useState<Artikle[] | []>([]);

    useEffect(() => {
        articlesAPI({
            method: "get",
            url: API_URL,
        })
            .then((res) => res.data)
            .then(
                (result) => {
                    setLatestArticles(result);
                    setIsLoaded(true);
                },
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                },
            );
    }, []);

    if (error && error?.message) {
        return <div>🛑 Ooops! {error.message} 🛑</div>;
    }

    if (!isLoaded) {
        return (
            <Container>
                <div className="container my-10 px-6 mx-auto">
                    <section className="mb-32 text-gray-800">
                        <h2 className="text-3xl font-bold mb-12 text-center">
                            🔎 Cargando... 🔍
                        </h2>
                    </section>
                </div>
            </Container>
        );
    }

    return (
        <Container>
            <div className="container my-20 px-6 mx-auto">
                <section className="mb-32 text-gray-800 text-center">
                    <h1 className="text-5xl font-bold mb-12 pb-4 text-center text-black">
                        🔎 Últimos artículos 🔍
                    </h1>
                    <div className="grid lg:grid-cols-3 gap-6 xl:gap-x-12">
                        {latestArticles &&
                            latestArticles.length > 0 &&
                            latestArticles.map((article, index) => (
                                <div
                                    className="mb-6 lg:mb-0"
                                    key={index}
                                    id={article.id}
                                >
                                    <div
                                        className="relative block bg-white rounded-lg shadow-lg text-black"
                                        style={{
                                            backgroundColor:
                                                "var(--powder-blue)",
                                        }}
                                    >
                                        <div className="flex">
                                            <div className="relative overflow-hidden bg-no-repeat bg-cover relative overflow-hidden bg-no-repeat bg-cover shadow-lg rounded-lg mx-4 -mt-4">
                                                <img
                                                    src={article.owner.logo}
                                                    className="w-full"
                                                />
                                                <a
                                                    href={article.url}
                                                    target="_blank"
                                                >
                                                    <div
                                                        className="absolute top-0 right-0 bottom-0 left-0 w-full h-full overflow-hidden bg-fixed opacity-0 hover:opacity-100 transition duration-300 ease-in-out"
                                                        style={{
                                                            backgroundColor:
                                                                "rgba(251, 251, 251, 0.15)",
                                                        }}
                                                    ></div>
                                                </a>
                                            </div>
                                        </div>
                                        <div className="p-6">
                                            <h5 className="font-bold text-lg mb-3">
                                                {article.title}
                                            </h5>
                                            <p
                                                className="mb-4"
                                                style={{
                                                    color: "var(--steel-teal)",
                                                }}
                                            >
                                                <small>
                                                    Publicado el{" "}
                                                    <u className="text-black">
                                                        {article.date}
                                                    </u>{" "}
                                                    por{" "}
                                                    <a
                                                        className="text-black"
                                                        href={
                                                            article.owner
                                                                .website
                                                        }
                                                        target="_blank"
                                                    >
                                                        {article.owner.name}
                                                    </a>
                                                </small>
                                            </p>
                                            <a
                                                style={{
                                                    backgroundColor:
                                                        "var(--steel-teal)",
                                                }}
                                                href={article.url}
                                                className="inline-block px-6 py-2.5 bg-blue-600 text-white font-medium text-md font-bold leading-tight uppercase rounded-full shadow-md hover:bg-blue-700 hover:shadow-lg focus:bg-blue-700 focus:shadow-lg focus:outline-none focus:ring-0 active:bg-blue-800 active:shadow-lg transition duration-150 ease-in-out"
                                                target="_blank"
                                            >
                                                Saber más
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            ))}
                    </div>
                </section>
            </div>
        </Container>
    );
};
