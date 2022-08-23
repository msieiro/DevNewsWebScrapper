import Document, {
    DocumentContext,
    DocumentInitialProps,
    Head,
    Html,
    Main,
    NextScript,
} from "next/document";

class CustomDocument extends Document {
    static async getInitialProps(
        ctx: DocumentContext,
    ): Promise<DocumentInitialProps> {
        const originalRenderPage = ctx.renderPage;

        ctx.renderPage = () =>
            originalRenderPage({
                enhanceApp: (App) => App,
                enhanceComponent: (Component) => Component,
            });

        const initialProps = await Document.getInitialProps(ctx);

        return initialProps;
    }

    render(): JSX.Element {
        return (
            <Html>
                <Head>
                    <meta name="theme-color" content="#000" />
                    <meta
                        name="description"
                        content="Visualiza titulares de artículos interesantes"
                    />
                    <meta
                        name="keywords"
                        content="artículos, desarrollo web, javascript, java, arquitectura de software, programación, programación funcional"
                    />
                    {/* <!-- Twitter Card data --> */}
                    <meta
                        name="twitter:title"
                        content="Dev Articles Visualizer"
                    />
                    <meta
                        name="twitter:description"
                        content="Visualiza titulares de artículos interesantes"
                    />
                    <meta
                        name="twitter:image"
                        content="/icons/android-chrome-192x192.png"
                    />
                    {/* <!-- Open Graph data --> */}
                    <meta
                        property="og:title"
                        content="Dev Articles Visualizer"
                    />
                    <meta property="og:type" content="article" />
                    <meta
                        property="og:url"
                        content="https://dev-news-web-scrapper.herokuapp.com/"
                    />
                    <meta
                        property="og:image"
                        content=" /icons/android-chrome-192x192.png"
                    />
                    <meta
                        property="og:description"
                        content="Visualiza titulares de artículos interesantes"
                    />
                    <meta
                        property="og:site_name"
                        content="Dev Articles Visualizer"
                    />
                    <link
                        rel="icon"
                        type="image/png"
                        href="/icons/favicon.ico"
                    />
                    <link
                        rel="icon"
                        type="image/png"
                        sizes="16x16"
                        href="/icons/favicon-16x16.png"
                    />
                    <link
                        rel="icon"
                        type="image/png"
                        sizes="32x32"
                        href="/icons/favicon-32x32.png"
                    />
                    <link
                        rel="apple-touch-icon"
                        href="/icons/apple-touch-icon.png"
                    />
                    <link rel="manifest" href="/icons/site.webmanifest" />
                    <link
                        rel="preconnect"
                        href="https://fonts.googleapis.com"
                    />
                    <link
                        rel="preconnect"
                        href="https://fonts.gstatic.com"
                        crossOrigin="crossorigin"
                    />
                </Head>
                <body>
                    <Main />
                    <NextScript />
                </body>
            </Html>
        );
    }
}

export default CustomDocument;
