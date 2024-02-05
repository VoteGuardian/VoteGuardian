import { useMediaQuery } from "react-responsive";

export const Mobile = ({children}) => {
    const isMobile = useMediaQuery({ query: "(max-width: 767px)" });

    return <>{isMobile && children}</>
}
export const Tablet = ({children}) => {
    const isTablet = useMediaQuery({ query: "(min-width:768px) and (max-width:1600px)" });

    return <>{isTablet && children}</>
}

export const PC = ({children}) => {
    const isPc = useMediaQuery({ query: "(min-width: 1601px)" });

    return <>{isPc && children}</>
}