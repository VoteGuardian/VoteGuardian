'use clinet'
import { GrCaretPrevious } from "react-icons/gr";
import { GrCaretNext } from "react-icons/gr";
import './Pagination.scss';
import { useRecoilState } from "recoil";
import { pageNumState } from "@/recoil/atoms/createVoteListState";

export default function Pagination() {
    const [currentPage, setCurrentPage] = useRecoilState(pageNumState);
    const limitPageCount = 5; //5개 단위로 페이지를 끊을 것
    //다른 데서 받았을 totalPageCount
    const totalPageCount = 8;//더미    

    //페이지를 5개씩 묶은 그룹 배열 생성하기
    const createPageGroupList = (totalPageCount, currentPage) => {
        //페이지 번호를 담을 전체 그룹
        const totalPageGroup = Array(totalPageCount).fill(currentPage).map((x, y) => x + y);
        //5페이지 단위로 그룹 나누기
        const pageGroupList = [];
        for(let i = 0; i < totalPageGroup.length; i += limitPageCount) {
            pageGroupList.push(totalPageGroup.slice(i, i+limitPageCount));
        }
        return pageGroupList;
    }
    
    //현재 페이지가 속한 그룹 구하기
    const getCurrentPageGroup = (currentPage, limitPageCount) => {
        return Math.ceil(currentPage/limitPageCount)-1;
    }

    let pageGroup = createPageGroupList(totalPageCount, 1);
    //페이지 그룹의 마지막 번호
    const pageLastGroup = pageGroup.length;
    //현재 페이지가 속한 그룹(페이지가 바뀌면 같이 바뀜)
    let currentGroup = getCurrentPageGroup(currentPage, limitPageCount);
    
    //이전 버튼 클릭 시
    //let previousPageGroup = 0;
    function handlePreviousPage() {
        //이동할 페이지는 이전 페이지 그룹의 마지막 숫자
        let previousPageGroup = currentGroup-1;
        setCurrentPage(pageGroup[previousPageGroup][4]);
        currentGroup = getCurrentPageGroup(currentPage, limitPageCount);
    }

    //다음 버튼 클릭 시
    //let nextPageGroup = 0;
    function handleNextPage() {
        //이동할 페이지는 다음 페이지 그룹의 첫 번째 숫자
        let nextPageGroup = currentGroup+1;
        setCurrentPage(pageGroup[currentGroup+1][0]);
    }

    //페이지 버튼 클릭 시 url 변경
    function handleChangePage(page) {

    }
    return (
        <div className="pagenation-wrap">
            {currentPage > 5 &&
                <div className="prev-button-wrap" onClick={handlePreviousPage}>
                    <GrCaretPrevious />
                    <p>이전</p>
                </div>
            }
            {pageGroup[currentGroup] && pageGroup[currentGroup].map((n) => {
                return(
                    <div>
                        <p>{n}</p>
                    </div>
                )
            })}
            {currentGroup !== pageLastGroup-1 &&
                <div className="next-button-wrap" onClick={handleNextPage}>
                    <p>다음</p>
                    <GrCaretNext />
                </div>
            }
        </div>
    )
}