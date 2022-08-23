import "@styles/global.scss";
import { AppProps } from "next/app";
import Head from "next/head";
import "tailwindcss/tailwind.css";

function MyApp({ Component, pageProps }: AppProps): JSX.Element {
    return (
        <>
            <Head>
                <title>Dev Articles Visualizer</title>
            </Head>
            <Component {...pageProps} />
        </>
    );
}

export default MyApp;
