/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/explicit-module-boundary-types */
export const Container = ({ children }: any) => {
    return (
        <div
            className="min-h-screen flex flex-col"
            style={{ backgroundColor: "var(--light-slate-gray)" }}
        >
            {children}
        </div>
    );
};
